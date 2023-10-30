package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

public class UpdateProductCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String barcode;

    private String sku;

    private String slug;

    @NotBlank(message = "name must be not blank")
    private String name;

    private String description;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;

    private BigDecimal price;

    @JsonProperty("price_per_item")
    private BigDecimal pricePerItem;

    private String status;

    private Set<String> assets;

    @JsonProperty("related_products")
    private Set<String> relatedProducts;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getAssets() {
        return assets;
    }

    public void setAssets(Set<String> assets) {
        this.assets = assets;
    }

    public Set<String> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(Set<String> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }
}
