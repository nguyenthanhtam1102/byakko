package com.byakko.service.production.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.common.valueobject.Money;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import com.byakko.service.production.domain.domaincore.valueobject.ProductStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Product extends BaseEntity<ProductId> {

    private String barcode;
    private String sku;
    private String slug;
    private String name;
    private String description;
    private ProductStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Asset image;
    private Set<Asset> assets;
    private Set<Product> relatedProducts;
    private Set<Option> options;
    private Set<ProductVariant> variants;

    public Product() {
    }

    public Product(ProductId productId) {
        super(productId);
    }

    public void initialize() {
        if(super.getId() == null)
            setId(new ProductId(UUID.randomUUID().toString()));
        if(createdAt == null)
            createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

    public void validate() {
        validateName();
        validateStatus();
    }

    private void validateName() {
        if(name == null || name.isBlank()) {
            throw new ValidationException(Map.of("name", "name must be not blank"));
        } else if(name.length() > 255) {
            throw new ValidationException(Map.of("name", "name maximum 255 characters"));
        }
    }

    private void validateStatus() {
        if(status == null) {
            throw new ValidationException(Map.of("status", "status must be not null"));
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Asset getImage() {
        return image;
    }

    public void setImage(Asset image) {
        this.image = image;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public Set<Product> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(Set<Product> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(Set<ProductVariant> variants) {
        this.variants = variants;
    }

}
