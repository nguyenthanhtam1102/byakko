package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CreateProductCommand {

    private String barcode;

    private String sku;

    private String slug;

    @NotBlank(message = "name must be not blank")
    private String name;

    private String description;

    @NotBlank(message = "status must be not blank")
    private String status;

    private Set<String> assets;

    @JsonProperty("related_products")
    private Set<String> relatedProducts;

    private Map<String, Set<String>> options;

    private Set<CreateVariantCommand> variants;

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

    public Map<String, Set<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Set<String>> options) {
        this.options = options;
    }

    public Set<CreateVariantCommand> getVariants() {
        return variants;
    }

    public void setVariants(Set<CreateVariantCommand> variants) {
        this.variants = variants;
    }
}
