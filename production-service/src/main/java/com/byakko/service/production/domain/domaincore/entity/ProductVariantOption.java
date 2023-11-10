package com.byakko.service.production.domain.domaincore.entity;

import java.util.Objects;

public class ProductVariantOption {

    private ProductVariant variant;
    private Option option;
    private OptionValue optionValue;

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public OptionValue getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(OptionValue optionValue) {
        this.optionValue = optionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariantOption that = (ProductVariantOption) o;
        return Objects.equals(variant, that.variant) && Objects.equals(option, that.option) && Objects.equals(optionValue, that.optionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant, option, optionValue);
    }

}
