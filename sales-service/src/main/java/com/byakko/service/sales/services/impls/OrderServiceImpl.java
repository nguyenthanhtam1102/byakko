package com.byakko.service.sales.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.sales.dtos.order.*;
import com.byakko.service.sales.mappers.OrderMapper;
import com.byakko.service.sales.models.*;
import com.byakko.service.sales.repositories.OrderRepository;
import com.byakko.service.sales.repositories.OrderStatusHistoryRepository;
import com.byakko.service.sales.services.OrderService;
import com.byakko.service.sales.vnpay.VNPayConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;

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
    public OrderResponse createOrder(CreateOrderCommand command) {
        Order order = new Order();

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
            orderDetail.setUnitPrice(od.getUnitPrice());
            return orderDetail;
        }).collect(Collectors.toSet());

        order.setOrderDetails(orderDetails);

        BigDecimal subTotal = orderDetails.stream()
                .map(od -> od.getUnitPrice().multiply(BigDecimal.valueOf(od.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDue = subTotal.subtract(order.getDeliveryCharge());

        order.setTotalDue(totalDue);

        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());
        orderStatusHistoryRepository.save(statusHistory);

        if(order.getPaymentMethod().equals(PaymentMethod.ONLINE)) {
            order.setStatus(OrderStatus.PENDING_PAYMENT);

            OrderStatusHistory sth = new OrderStatusHistory();
            sth.setOrder(order);
            sth.setStatus(order.getStatus());
            orderStatusHistoryRepository.save(statusHistory);
        }

        orderRepository.save(order);

        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public String paymentOrder(PaymentOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", command.getOrderId())));

        if(!order.getStatus().equals(OrderStatus.PENDING_PAYMENT))
            throw new RuntimeException("Đơn hàng không thuộc trạng thái đang chờ thanh toán");

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Amount", String.valueOf(order.getTotalDue().multiply(BigDecimal.valueOf(100))));
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
    public void orderPaymentSuccess(OrderPaymentSuccessDTO dto) {
        Order order = orderRepository.findById(dto.getTxnRef())
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", dto.getTxnRef())));

        order.setStatus(OrderStatus.PAYMENT_SUCCESS);

        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());

        orderStatusHistoryRepository.save(statusHistory);

        order.setStatus(OrderStatus.PROCESSING);

        OrderStatusHistory statusHistory2 = new OrderStatusHistory();
        statusHistory2.setOrder(order);
        statusHistory2.setStatus(order.getStatus());

        orderStatusHistoryRepository.save(statusHistory2);

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
}
