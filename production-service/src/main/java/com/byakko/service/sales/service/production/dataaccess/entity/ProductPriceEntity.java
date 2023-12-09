package com.byakko.service.sales.service.production.dataaccess.entity;

import com.byakko.service.sales.common.valueobject.Money;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_prices")
@IdClass(ProductPriceEntityId.class)
@AttributeOverrides({
        @AttributeOverride(name = "originalPrice.amount", column = @Column(name = "original_price", nullable = false)),
        @AttributeOverride(name = "pricePerItem.amount", column = @Column(name = "price_per_item", nullable = false)),
        @AttributeOverride(name = "price.amount", column = @Column(name = "price", nullable = false))
})
public class ProductPriceEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Include
    private ProductEntity product;

    @Id
    @Column(name = "start_date")
    @EqualsAndHashCode.Include
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Embedded
    private Money originalPrice;

    @Embedded
    private Money price;

    @Embedded
    private Money pricePerItem;

    private Boolean active;

    @Column(length = 512)
    private String reason;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
