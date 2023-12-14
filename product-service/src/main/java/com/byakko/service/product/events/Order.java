package com.byakko.service.product.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @EqualsAndHashCode.Include
    private String id;

    private Long orderDate;

    private String customer;

    private String shippingAddress;

    private String note;

    private String phone;

    private Set<OrderDetail> orderDetails;

    private BigDecimal subTotal;

    private BigDecimal deliveryCharge;

    private BigDecimal totalDue;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", customer='" + customer + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", note='" + note + '\'' +
                ", phone='" + phone + '\'' +
                ", orderDetails=" + orderDetails +
                ", subTotal=" + subTotal +
                ", deliveryCharge=" + deliveryCharge +
                ", totalDue=" + totalDue +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
