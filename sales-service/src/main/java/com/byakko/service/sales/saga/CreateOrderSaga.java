package com.byakko.service.sales.saga;

import com.byakko.infrastructure.kafka.constants.KafkaTopic;
import com.byakko.infrastructure.kafka.producer.KafkaProducer;
import com.byakko.service.sales.dtos.order.OrderResponse;
import com.byakko.service.sales.events.OrderCheckedEvent;
import com.byakko.service.sales.events.OrderCreatedEvent;
import com.byakko.service.sales.mappers.OrderMapper;
import com.byakko.service.sales.models.Order;
import com.byakko.service.sales.models.OrderStatus;
import com.byakko.service.sales.models.OrderStatusHistory;
import com.byakko.service.sales.repositories.OrderRepository;
import com.byakko.service.sales.repositories.OrderStatusHistoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderSaga {

    @Value("${kafka.retry}")
    private int KAFKA_RETRY;

    @Value("${request.timeout}")
    private int REQUEST_TIMEOUT;

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final WebClient webClient;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    public static final Map<String, MonoSink<OrderResponse>> pendingRequests = new ConcurrentHashMap<>();

    public Mono<OrderResponse> createOrder(Order order) {
        System.out.println("Xử lí creating đơn hàng");
        return Mono.create((MonoSink<OrderResponse> sink) -> {
                    try {
                        order.setStatus(OrderStatus.PENDING);
                        orderRepository.save(order);
                        addStatusHistory(order);

                        pendingRequests.put(order.getId(), sink);
                    } catch (Exception ex) {
                        createOrderRollback(order.getId(), order);
                    }

                    sendOrderCreatedEvent(
                            order.getId(),
                            new OrderCreatedEvent(order.getId(), order, ZonedDateTime.now().toEpochSecond())
                    );
                }).timeout(Duration.ofSeconds(REQUEST_TIMEOUT), Mono.create(sink -> {
                    createOrderRollback(order.getId(), order);
                }))
                .doOnError(throwable -> {
                    createOrderRollback(order.getId(), order);
                })
                .doFinally(signalType -> pendingRequests.remove(order.getId()));
    }

    private void createOrderRollback(String eventId, Order order) {
        order.setStatus(OrderStatus.FAILED);
        orderRepository.save(order);
        addStatusHistory(order);

        MonoSink<OrderResponse> sink = pendingRequests.get(eventId);
        if(sink != null) {
            sink.error(new RuntimeException("Tao don hang khong thanh cong"));
            pendingRequests.remove(eventId);
        }
    }

    private void sendOrderCreatedEvent(String eventId, OrderCreatedEvent event) {
        System.out.println("Gửi orderCreatedEvent sang product-service");

        String orderCreatedEventJson;
        try {
            orderCreatedEventJson = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            log.error("Khong the chuyen event to json");
            return;
        }

        kafkaProducer.send(KafkaTopic.ORDER_CREATED_TOPIC, orderCreatedEventJson)
                .retry(KAFKA_RETRY)
                .doOnError(throwable -> {
                    event.getOrder().setStatus(OrderStatus.FAILED);
                    orderRepository.save(event.getOrder());
                    addStatusHistory(event.getOrder());

                    MonoSink<OrderResponse> sink = pendingRequests.get(eventId);
                    if(sink != null) {
                        sink.error(throwable);
                        pendingRequests.remove(eventId);
                    }
                })
                .subscribe();
    }

    public void checkedOrder(OrderCheckedEvent orderCheckedEvent) {
        System.out.println("Message received: " + orderCheckedEvent);

        MonoSink<OrderResponse> sink = pendingRequests.get(orderCheckedEvent.getId());
        if(sink != null) {
            Order order = orderCheckedEvent.getOrder();

            order.getOrderDetails().forEach(orderDetail -> {
                orderDetail.setOrder(new Order(order.getId()));
            });

            BigDecimal subTotal = order.getOrderDetails().stream()
                    .map(orderDetail -> orderDetail.getUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setSubTotal(subTotal);

            BigDecimal totalDue = subTotal.add(order.getDeliveryCharge());
            order.setTotalDue(totalDue);

            order.setStatus(OrderStatus.PENDING_APPROVAL);
            orderRepository.save(order);
            addStatusHistory(order);
            sink.success(OrderMapper.toOrderResponse(order));
        }
    }

    private void addStatusHistory(Order order) {
        OrderStatusHistory statusHistory = new OrderStatusHistory();
        statusHistory.setOrder(order);
        statusHistory.setStatus(order.getStatus());
        orderStatusHistoryRepository.save(statusHistory);
    }

}
