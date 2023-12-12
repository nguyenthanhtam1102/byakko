package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    private String note;

    @Column(length = 255,nullable = false)
    private String phone;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "delivery_charge", nullable = false)
    private BigDecimal deliveryCharge;

    @Column(name = "total_due", nullable = false)
    private BigDecimal totalDue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderStatusHistory> statusHistories;

    public Order() {
        id = UUID.randomUUID().toString().replace("-", "");
        orderDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        status = OrderStatus.CREATED;
    }

}
