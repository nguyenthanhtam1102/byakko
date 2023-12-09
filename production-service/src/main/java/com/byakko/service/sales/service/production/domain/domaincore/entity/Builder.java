package com.byakko.service.sales.service.production.domain.domaincore.entity;

public final class Builder {
    private ProductVariant variant;
    private Option option;
    private OptionValue optionValue;

    private Builder() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder variant(ProductVariant variant) {
        this.variant = variant;
        return this;
    }

    public Builder option(Option option) {
        this.option = option;
        return this;
    }

    public Builder optionValue(OptionValue optionValue) {
        this.optionValue = optionValue;
        return this;
    }

    public ProductVariantOption build() {
        ProductVariantOption productVariantOption = new ProductVariantOption();
        productVariantOption.setVariant(variant);
        productVariantOption.setOption(option);
        productVariantOption.setOptionValue(optionValue);
        return productVariantOption;
    }
}
