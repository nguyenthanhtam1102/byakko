package com.byakko.service.sales.service.product.models;

import com.byakko.service.sales.common.DomainConstants;
import lombok.*;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String barcode;

    private String name;

    private String sku;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_to_assets",
            joinColumns =@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private Set<Asset> assets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_to_categories",
            joinColumns =@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Option> options;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductVariant> variants;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updated;

    private boolean deleted;

    public Product() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updated = createdAt;
    }

    @Override
    public String toString() {
        return "Product{}";
    }
}
