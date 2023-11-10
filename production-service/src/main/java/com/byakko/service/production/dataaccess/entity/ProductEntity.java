package com.byakko.service.production.dataaccess.entity;

import com.byakko.common.valueobject.Money;
import com.byakko.service.production.domain.domaincore.valueobject.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String barcode;

    private String sku;

    private String slug;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private AssetEntity image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_to_assets",
            joinColumns =@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private Set<AssetEntity> assets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "related_products",
            joinColumns =@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "related_product_id")
    )
    private Set<ProductEntity> relatedProducts;

    public ProductEntity(String id) {
        this.id = id;
    }

}
