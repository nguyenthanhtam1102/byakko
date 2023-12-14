package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
import com.google.gson.annotations.Expose;
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
    private Long orderDate;

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
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "delivery_charge", nullable = false)
    private BigDecimal deliveryCharge = BigDecimal.ZERO;

    @Column(name = "total_due", nullable = false)
    private BigDecimal totalDue = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Expose
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Expose
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @Expose
    private List<OrderStatusHistory> statusHistories;

    public Order() {
        id = UUID.randomUUID().toString().replace("-", "");
        orderDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond();
        status = OrderStatus.CREATED;
    }

    public Order(String id) {
        this.id = id;
    }
}
