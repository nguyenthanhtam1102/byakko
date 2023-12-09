package com.byakko.service.sales.service.product.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    private BigDecimal discount;

    private BigDecimal surcharge;

    private BigDecimal tax;

    @Column(name = "delivery_charge")
    private BigDecimal deliveryCharge;

    @Column(name = "total_due", nullable = false)
    private BigDecimal totalDue;

    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @Column(name = "ship_date")
    private ZonedDateTime shipDate;

    private String note;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    private boolean deleted;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    private Set<GoodsReceipt> goodsReceipt;

}
