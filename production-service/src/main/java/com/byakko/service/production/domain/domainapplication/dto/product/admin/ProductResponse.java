package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import com.byakko.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.production.domain.domainapplication.dto.global_option.OptionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProductResponse {

    private String id;

    private String barcode;

    private String sku;

    private String slug;

    private String name;

    private String description;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;

    private BigDecimal price;

    @JsonProperty("price_per_item")
    private BigDecimal pricePerItem;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    private Set<AssetResponse> assets;

    private Set<ProductItemResponse> relatedProducts;

    private List<OptionResponse> options;
    private List<ProductVariantResponse> variants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
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

    public Set<ProductItemResponse> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(Set<ProductItemResponse> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public void setOptions(List<OptionResponse> options) {
        this.options = options;
    }

    public List<ProductVariantResponse> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariantResponse> variants) {
        this.variants = variants;
    }

    public static final class Builder {
        private String id;
        private String barcode;
        private String sku;
        private String slug;
        private String name;
        private String description;
        private BigDecimal originalPrice;
        private BigDecimal price;
        private BigDecimal pricePerItem;
        private long createdAt;
        private Long updatedAt;
        private Set<AssetResponse> assets;
        private Set<ProductItemResponse> relatedProducts;
        private List<OptionResponse> options;
        private List<ProductVariantResponse> variants;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
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

        public Builder originalPrice(BigDecimal originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder pricePerItem(BigDecimal pricePerItem) {
            this.pricePerItem = pricePerItem;
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

        public Builder relatedProducts(Set<ProductItemResponse> relatedProducts) {
            this.relatedProducts = relatedProducts;
            return this;
        }

        public Builder options(List<OptionResponse> options) {
            this.options = options;
            return this;
        }

        public Builder variants(List<ProductVariantResponse> variants) {
            this.variants = variants;
            return this;
        }

        public ProductResponse build() {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(id);
            productResponse.setBarcode(barcode);
            productResponse.setSku(sku);
            productResponse.setSlug(slug);
            productResponse.setName(name);
            productResponse.setDescription(description);
            productResponse.setOriginalPrice(originalPrice);
            productResponse.setPrice(price);
            productResponse.setPricePerItem(pricePerItem);
            productResponse.setCreatedAt(createdAt);
            productResponse.setUpdatedAt(updatedAt);
            productResponse.setAssets(assets);
            productResponse.setRelatedProducts(relatedProducts);
            productResponse.setOptions(options);
            productResponse.setVariants(variants);
            return productResponse;
        }
    }
}
