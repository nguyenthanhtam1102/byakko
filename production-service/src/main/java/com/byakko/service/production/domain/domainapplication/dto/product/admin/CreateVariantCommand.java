package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import java.util.Map;
import java.util.Objects;

public class CreateVariantCommand {

    Map<String, String> variantOptions;

    private String sku;

    public Map<String, String> getVariantOptions() {
        return variantOptions;
    }

    public void setVariantOptions(Map<String, String> variantOptions) {
        this.variantOptions = variantOptions;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateVariantCommand that = (CreateVariantCommand) o;
        return Objects.equals(variantOptions, that.variantOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variantOptions);
    }
}
