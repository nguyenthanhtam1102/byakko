package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductFilterItemResponse {

    private String id;
    private String slug;
    private String name;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;
    private BigDecimal price;
    private AssetResponse image;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AssetResponse getImage() {
        return image;
    }

    public void setImage(AssetResponse image) {
        this.image = image;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static final class Builder {
        private String id;
        private String slug;
        private String name;
        private BigDecimal originalPrice;
        private BigDecimal price;
        private AssetResponse image;
        private long createdAt;
        private Long updatedAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
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

        public Builder originalPrice(BigDecimal originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder image(AssetResponse image) {
            this.image = image;
            return this;
        }

        public Builder createdAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProductFilterItemResponse build() {
            ProductFilterItemResponse productFilterItemResponse = new ProductFilterItemResponse();
            productFilterItemResponse.setId(id);
            productFilterItemResponse.setSlug(slug);
            productFilterItemResponse.setName(name);
            productFilterItemResponse.setOriginalPrice(originalPrice);
            productFilterItemResponse.setPrice(price);
            productFilterItemResponse.setImage(image);
            productFilterItemResponse.setCreatedAt(createdAt);
            productFilterItemResponse.setUpdatedAt(updatedAt);
            return productFilterItemResponse;
        }
    }
}
