package com.byakko.service.sales.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.sales.dtos.order.*;
import com.byakko.service.sales.mappers.OrderMapper;
import com.byakko.service.sales.models.*;
import com.byakko.service.sales.models.ghn.GHNCreateOrderResponse;
import com.byakko.service.sales.models.ghn.GHNOrder;
import com.byakko.service.sales.models.ghn.GHNOrderItem;
import com.byakko.service.sales.repositories.OrderPaymentStatusHistoryRepository;
import com.byakko.service.sales.repositories.OrderRepository;
import com.byakko.service.sales.repositories.OrderStatusHistoryRepository;
import com.byakko.service.sales.saga.CreateOrderSaga;
import com.byakko.service.sales.services.OrderService;
import com.byakko.service.sales.vnpay.VNPayConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final OrderPaymentStatusHistoryRepository orderPaymentStatusHistoryRepository;
    private final CreateOrderSaga createOrderSaga;
    private final WebClient webClient;

    @Override
    public ListAllOrderResponse listAllOrders(ListAllOrderCommand command) {
        Sort.Direction direction;
        if(command.getSortDirection() == null || command.getSortDirection().isBlank()) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else {
            throw new RuntimeException("Sort direction not correct");
        }

        Sort sort;
        if(command.getSortBy() == null || command.getSortBy().isBlank()) {
            sort = Sort.by(direction, "orderDate");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<Order> page = orderRepository.search(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllOrderResponse.builder()
                .data(page.stream().map(OrderMapper::toOrderResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public OrderResponse getOrder(GetOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", command.getOrderId())));
        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public Mono<OrderResponse> createOrder(CreateOrderCommand command) {
        Order order = new Order();
        order.setPhone(command.getPhone());
        order.setDeliveryCharge(command.getDeliveryCharge());

        order.setCustomer(command.getCustomerId());
        order.setShippingAddress(command.getShippingAddress());
        order.setNote(command.getNote());
        order.setPaymentMethod(PaymentMethod.valueOf(command.getPaymentMethod()));

        Set<OrderDetail> orderDetails = command.getOrderDetails().stream().map(od -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(od.getProduct());
            orderDetail.setVariant(od.getVariant());
            orderDetail.setQuantity(od.getQuantity());
            return orderDetail;
        }).collect(Collectors.toSet());

        order.setOrderDetails(orderDetails);

        if(order.getPaymentMethod().equals(PaymentMethod.ONLINE)) {
            order.setPaymentStatus(OrderPaymentStatus.PENDING_PAYMENT);
            System.out.println("Payment status: " + order.getPaymentStatus());
            orderRepository.save(order);

            OrderPaymentStatusHistory orderPaymentStatusHistory = new OrderPaymentStatusHistory();
            orderPaymentStatusHistory.setStatus(order.getPaymentStatus());
            orderPaymentStatusHistory.setOrder(order);
            orderPaymentStatusHistory.setTimestamp(ZonedDateTime.now().toEpochSecond());
            orderPaymentStatusHistoryRepository.save(orderPaymentStatusHistory);
        }

        return createOrderSaga.createOrder(order);     
    }

    @Override
    public String paymentOrder(PaymentOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", command.getOrderId())));

        if(!order.getStatus().equals(OrderStatus.PENDING_PAYMENT))
            throw new RuntimeException("Đơn hàng không thuộc trạng thái đang chờ thanh toán");

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Amount", String.valueOf(order.getTotalDue().multiply(BigDecimal.valueOf(100)).intValue()));
        vnp_Params.put("vnp_Command", VNPayConfigs.vnp_Command);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_IpAddr", command.getIpAddress());
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + order.getId());
        vnp_Params.put("vnp_OrderType", VNPayConfigs.orderType);
        vnp_Params.put("vnp_ReturnUrl", VNPayConfigs.vnp_ReturnUrl);
        vnp_Params.put("vnp_TmnCode", VNPayConfigs.vnp_TmnCode);
        vnp_Params.put("vnp_TxnRef", order.getId());
        vnp_Params.put("vnp_Version", VNPayConfigs.vnp_Version);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');

                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (Exception ex) {
                    throw new RuntimeException("Thanh toan khong thanh cong");
                }

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfigs.hmacSHA512(VNPayConfigs.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfigs.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    @Override
    public void orderPaymentSuccess(String TxnRef) {
        Order order = orderRepository.findById(TxnRef)
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", TxnRef)));

        order.setPaymentStatus(OrderPaymentStatus.PAID);

        OrderPaymentStatusHistory statusHistory = new OrderPaymentStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getPaymentStatus());

        orderPaymentStatusHistoryRepository.save(statusHistory);

        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(CancelOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", command.getOrderId())));

        order.setStatus(OrderStatus.CANCELED);

        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());

        orderStatusHistoryRepository.save(statusHistory);

        orderRepository.save(order);
    }

    @Override
    public Mono<String> approvalOrder(ApprovelOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", command.getOrderId())));

        order.setStatus(OrderStatus.APPROVAL);

        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());
        orderStatusHistoryRepository.save(statusHistory);

        order.setStatus(OrderStatus.PROCESSING);

        statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());
        orderStatusHistoryRepository.save(statusHistory);

        orderRepository.save(order);

        return createGhnOrder(order);
    }

    private Mono<String> createGhnOrder(Order order) {
        GHNOrder ghnOrder = GHNOrder.builder()
                .required_note("KHONGCHOXEMHANG")
                .payment_type_id(1)
                .from_name("Byakko")
                .from_phone("0987654321")
                .from_address("72 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Vietnam")
                .from_ward_name("Phường 14")
                .from_district_name("Quận 10")
                .from_province_name("HCM")
                .to_name(order.getCustomer())
                .to_phone(order.getPhone())
                .to_address("152/10 Lương Ngọc Quyến, Phường 5, Quận Gò Vấp, TP.HCM")
                .to_ward_code("20308")
                .to_district_id(1444)
                .cod_amount(order.getTotalDue().intValue())
                .weight(200)
                .service_type_id(5)
                .insurance_value(order.getSubTotal().intValue())
                .items(order.getOrderDetails().stream().map(orderDetail -> GHNOrderItem.builder()
                        .name(orderDetail.getProduct())
                        .code(orderDetail.getProduct())
                        .price(orderDetail.getUnitPrice().intValue())
                        .quantity(orderDetail.getQuantity())
                        .weight(100)
                        .build()).toList())
                .build();

        if(order.getPaymentMethod() == PaymentMethod.ONLINE) {
            ghnOrder.setCod_amount(0);
        }

        return webClient.post()
                .uri("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create")
                .header("Token", "4e38d1bb-98a6-11ee-8bfa-8a2dda8ec551")
                .header("ShopId", "190530")
                .body(Mono.just(ghnOrder), GHNOrder.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody ->
                                Mono.error(new RuntimeException("Lỗi 400 BadRequest: " + errorBody))
                        )
                )
                .bodyToMono(GHNCreateOrderResponse.class)
                .retry(3)
                .map(response -> {
                    if ("200".equals(response.getCode())) {
                        return "OK";
                    } else {
                        throw new RuntimeException("Lỗi: Mã phản hồi không phải là 200");
                    }
                })
                .onErrorResume(throwable ->
                        Mono.just("Xử lý lỗi: " + throwable.getMessage())
                );
    }

}
