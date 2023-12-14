package com.byakko.service.sales.services;

import com.byakko.service.sales.dtos.order.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public interface OrderService {

    ListAllOrderResponse listAllOrders(@Valid ListAllOrderCommand command);
    OrderResponse getOrder(@Valid GetOrderCommand command);
    Mono<OrderResponse> createOrder(@Valid CreateOrderCommand command);
    String paymentOrder(@Valid PaymentOrderCommand command);
    void orderPaymentSuccess(@Valid String TxnRef);
    void cancelOrder(@Valid CancelOrderCommand command);
    Mono<String> approvalOrder(@Valid ApprovelOrderCommand command);

}
