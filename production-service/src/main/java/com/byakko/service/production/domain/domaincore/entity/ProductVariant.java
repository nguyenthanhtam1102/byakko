package com.byakko.service.production.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.service.production.domain.domaincore.valueobject.ProductVariantId;

import java.util.Set;

public class ProductVariant extends BaseEntity<ProductVariantId> {

    private String sku;

    private Set<ProductVariantOption> variantOptions;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Set<ProductVariantOption> getVariantOptions() {
        return variantOptions;
    }

    public void setVariantOptions(Set<ProductVariantOption> variantOptions) {
        this.variantOptions = variantOptions;
    }
}
