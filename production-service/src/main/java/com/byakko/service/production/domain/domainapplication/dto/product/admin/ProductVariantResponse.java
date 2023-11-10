package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import java.util.Map;

public class ProductVariantResponse {

    private String id;

    private Map<String, String> options;

    private String sku;

    public ProductVariantResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
