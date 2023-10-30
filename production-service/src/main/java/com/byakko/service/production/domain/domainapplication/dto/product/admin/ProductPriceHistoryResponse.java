package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductPriceHistoryResponse {

    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("end_date")
    private Long endDate;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("price_per_item")
    private BigDecimal pricePerItem;

    @JsonProperty("reason")
    private String reason;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static final class Builder {
        private Long startDate;
        private Long endDate;
        private BigDecimal originalPrice;
        private BigDecimal price;
        private BigDecimal pricePerItem;
        private String reason;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder startDate(Long startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Long endDate) {
            this.endDate = endDate;
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

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public ProductPriceHistoryResponse build() {
            ProductPriceHistoryResponse productPriceHistoryResponse = new ProductPriceHistoryResponse();
            productPriceHistoryResponse.setStartDate(startDate);
            productPriceHistoryResponse.setEndDate(endDate);
            productPriceHistoryResponse.setOriginalPrice(originalPrice);
            productPriceHistoryResponse.setPrice(price);
            productPriceHistoryResponse.setPricePerItem(pricePerItem);
            productPriceHistoryResponse.setReason(reason);
            return productPriceHistoryResponse;
        }
    }
}
