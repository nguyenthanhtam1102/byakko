package com.byakko.service.sales.service.product.models;

import com.byakko.service.sales.common.DomainConstants;
import lombok.*;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "goods_receipt_details")
public class GoodReceiptDetail {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_receipt_id", nullable = false)
    private GoodsReceipt goodsReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_detail_id", nullable = false)
    private PurchaseOrderDetail purchaseOrderDetail;

    @Column(name = "order_qty", nullable = false)
    private Integer orderQuantity;

    @Column(name = "received_qty", nullable = false)
    private Integer receivedQuantity;

    @Column(name = "rejected_qty", nullable = false)
    private Integer rejectedQuantity;

    private String note;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    public GoodReceiptDetail() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }

}
