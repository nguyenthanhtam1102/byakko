package com.byakko.service.product.models;

import com.byakko.common.DomainConstants;
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
@Table(name = "inventories")
public class Inventory {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", unique = true)
    private ProductVariant variant;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    public Inventory() {
        id = UUID.randomUUID().toString().replace("-", "");
        startDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = startDate;
    }

}
