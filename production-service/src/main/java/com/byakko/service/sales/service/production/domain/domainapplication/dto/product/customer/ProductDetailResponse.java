package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDetailResponse {

    private String id;

    private String slug;

    private String name;

    private String description;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;

    private BigDecimal price;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    private Set<AssetResponse> assets;

    private Set<ProductFilterItemResponse> relatedProducts;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<AssetResponse> getAssets() {
        return assets;
    }

    public void setAssets(Set<AssetResponse> assets) {
        this.assets = assets;
    }

    public Set<ProductFilterItemResponse> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(Set<ProductFilterItemResponse> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public static final class Builder {
        private String id;
        private String slug;
        private String name;
        private String description;
        private BigDecimal originalPrice;
        private BigDecimal price;
        private long createdAt;
        private Long updatedAt;
        private Set<AssetResponse> assets;
        private Set<ProductFilterItemResponse> relatedProducts;

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

        public Builder description(String description) {
            this.description = description;
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

        public Builder createdAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder assets(Set<AssetResponse> assets) {
            this.assets = assets;
            return this;
        }

        public Builder relatedProducts(Set<ProductFilterItemResponse> relatedProducts) {
            this.relatedProducts = relatedProducts;
            return this;
        }

        public ProductDetailResponse build() {
            ProductDetailResponse productDetailResponse = new ProductDetailResponse();
            productDetailResponse.setId(id);
            productDetailResponse.setSlug(slug);
            productDetailResponse.setName(name);
            productDetailResponse.setDescription(description);
            productDetailResponse.setOriginalPrice(originalPrice);
            productDetailResponse.setPrice(price);
            productDetailResponse.setCreatedAt(createdAt);
            productDetailResponse.setUpdatedAt(updatedAt);
            productDetailResponse.setAssets(assets);
            productDetailResponse.setRelatedProducts(relatedProducts);
            return productDetailResponse;
        }
    }
}
