package com.byakko.service.production.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.common.valueobject.Money;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class ProductPrice {

    private Product product;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Money originalPrice;
    private Money price;
    private Money pricePerItem;
    private String reason;
    private Boolean active;

    public void initialize() {
        if(startDate == null)
            startDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

    public void validate() {
        validateProduct();
        validateStartDate();
        validateOriginalPrice();
        validatePrice();
        validatePricePerItem();
    }

    private void validateProduct() {
        if(product == null) {
            throw new ValidationException(Map.of("product", "product must be not null"));
        }
    }

    private void validateStartDate() {
        if(startDate == null) {
            throw new ValidationException(Map.of("startDate", "startDate must be not null"));
        }
    }

    private void validateOriginalPrice() {
        if(originalPrice == null) {
            throw new ValidationException(Map.of("originalPrice", "originalPrice must be greater than or equal 0"));
        } else if(!originalPrice.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("originalPrice", "originalPrice must be greater than or equal 0"));
        }
    }

    private void validatePrice() {
        if(price == null) {
            throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
        } else if(!price.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
        }
    }

    private void validatePricePerItem() {
        if(pricePerItem == null) {
            throw new ValidationException(Map.of("pricePerItem", "pricePerItem must be greater than or equal 0"));
        } else if(!pricePerItem.isGreaterThanOrEqualZero()) {
            throw new ValidationException(Map.of("pricePerItem", "pricePerItem must be greater than or equal 0"));
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Money getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Money originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(Money pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductPriceHistory{" +
                "product=" + product +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", originalPrice=" + originalPrice +
                ", price=" + price +
                ", pricePerItem=" + pricePerItem +
                ", reason='" + reason + '\'' +
                '}';
    }

    public static final class Builder {
        private Product product;
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;
        private Money originalPrice;
        private Money price;
        private Money pricePerItem;
        private String reason;
        private Boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder startDate(ZonedDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(ZonedDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder originalPrice(Money originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder pricePerItem(Money pricePerItem) {
            this.pricePerItem = pricePerItem;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder active(Boolean active) {
            this.active = active;
            return this;
        }

        public ProductPrice build() {
            ProductPrice productPrice = new ProductPrice();
            productPrice.setProduct(product);
            productPrice.setStartDate(startDate);
            productPrice.setEndDate(endDate);
            productPrice.setOriginalPrice(originalPrice);
            productPrice.setPrice(price);
            productPrice.setPricePerItem(pricePerItem);
            productPrice.setReason(reason);
            productPrice.setActive(active);
            return productPrice;
        }
    }
}
