package com.byakko.service.product.models;

import com.byakko.common.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "product_prices",
        indexes = {
            @Index(name = "idx_product_price_dates", columnList = "start_date, end_date"),
            @Index(name = "idx_product_price_active", columnList = "active"),
            @Index(name = "idx_product_price_product", columnList = "product_id")
        }
)
public class ProductPrice {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", unique = true)
    private ProductVariant variant;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = true)
    private Employee employee;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private BigDecimal price;

    private String note;

    private boolean active = true;

    @Column(name = "modified_date", nullable = false)
    private ZonedDateTime modifiedDate;

    private boolean deleted;

    public ProductPrice() {
        id = UUID.randomUUID().toString().replace("-", "");
        modifiedDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

}
