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
    private Money originalPrice;
    private Money price;
    private Money pricePerItem;
    private ProductStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Asset image;
    private Set<Asset> assets;
    private Set<Product> relatedProducts;

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
        validateOriginalPrice();
        validatePrice();
        validatePricePerItem();
        validateStatus();
    }

    private void validateName() {
        if(name == null || name.isBlank()) {
            throw new ValidationException(Map.of("name", "name must be not blank"));
        } else if(name.length() > 255) {
            throw new ValidationException(Map.of("name", "name maximum 255 characters"));
        }
    }

    private void validateOriginalPrice() {
        if(originalPrice == null) {
            throw new ValidationException(Map.of("originalPrice", "originalPrice must be greater than or equal 0"));
        } else if(!originalPrice.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("originalPrice", "originalPrice must be greater than or equal 0"));
        }
    }

    private void validatePrice() {
        if(price == null) {
            throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
        } else if(!price.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
        }
    }

    private void validatePricePerItem() {
        if(pricePerItem == null) {
            throw new ValidationException(Map.of("pricePerItem", "pricePerItem must be greater than or equal 0"));
        } else if(!pricePerItem.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("pricePerItem", "pricePerItem must be greater than or equal 0"));
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

    public Money getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Money originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(Money pricePerItem) {
        this.pricePerItem = pricePerItem;
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

    public static final class Builder {
        private ProductId id;
        private String barcode;
        private String sku;
        private String slug;
        private String name;
        private String description;
        private Money originalPrice;
        private Money price;
        private Money pricePerItem;
        private ProductStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private Asset image;
        private Set<Asset> assets;
        private Set<Product> relatedProducts;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ProductId id) {
            this.id = id;
            return this;
        }

        public Builder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder originalPrice(Money originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder pricePerItem(Money pricePerItem) {
            this.pricePerItem = pricePerItem;
            return this;
        }

        public Builder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder image(Asset image) {
            this.image = image;
            return this;
        }

        public Builder assets(Set<Asset> assets) {
            this.assets = assets;
            return this;
        }

        public Builder relatedProducts(Set<Product> relatedProducts) {
            this.relatedProducts = relatedProducts;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setBarcode(barcode);
            product.setSku(sku);
            product.setSlug(slug);
            product.setName(name);
            product.setDescription(description);
            product.setOriginalPrice(originalPrice);
            product.setPrice(price);
            product.setPricePerItem(pricePerItem);
            product.setStatus(status);
            product.setCreatedAt(createdAt);
            product.setUpdatedAt(updatedAt);
            product.setImage(image);
            product.setAssets(assets);
            product.setRelatedProducts(relatedProducts);
            return product;
        }
    }
}
