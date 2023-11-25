package com.byakko.service.sales.mappers;

import com.byakko.service.sales.dtos.order.OrderDetailResponse;
import com.byakko.service.sales.dtos.order.OrderResponse;
import com.byakko.service.sales.dtos.order.OrderStatusHistoryResponse;
import com.byakko.service.sales.models.Order;
import com.byakko.service.sales.models.OrderDetail;
import com.byakko.service.sales.models.OrderStatusHistory;

public class OrderMapper {

    public static OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customer(order.getCustomer())
                .orderDate(order.getOrderDate().toEpochSecond())
                .shippingAddress(order.getShippingAddress())
                .note(order.getNote())
                .orderStatus(order.getStatus().name())
                .paymentMethod(order.getPaymentMethod().name())
                .subTotal(order.getSubTotal())
                .deliveryCharge(order.getDeliveryCharge())
                .totalDue(order.getTotalDue())
                .orderDetails(order.getOrderDetails() != null
                        ? order.getOrderDetails().stream().map(OrderMapper::toOrderDetailResponse).toList()
                        : null)
                .statusHistories(order.getStatusHistories() != null ?
                        order.getStatusHistories().stream().map(OrderMapper::toOrderStatusHistoryResponse).toList()
                        : null)
                .build();
    }

    public static OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .product(orderDetail.getProduct())
                .variant(orderDetail.getVariant())
                .quantity(orderDetail.getQuantity())
                .unitPrice(orderDetail.getUnitPrice())
                .build();
    }

    public static OrderStatusHistoryResponse toOrderStatusHistoryResponse(OrderStatusHistory statusHistory) {
        return OrderStatusHistoryResponse.builder()
                .status(statusHistory.getStatus().name())
                .timestamp(statusHistory.getTimestamp().toEpochSecond())
                .note(statusHistory.getNote())
                .build();
    }

}
