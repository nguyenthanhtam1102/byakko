package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.MoneyResponse;
import com.byakko.service.product.dtos.product_price.ProductPriceResponse;
import com.byakko.service.product.models.ProductPrice;

public class ProductPriceMapper {

    public static ProductPriceResponse toProductPriceResponse(ProductPrice productPrice) {
        return ProductPriceResponse.builder()
                .id(productPrice.getId())
                .employee(productPrice.getEmployee().getId())
                .price(MoneyResponse.toMoneyResponse(productPrice.getPrice()))
                .startDate(productPrice.getStartDate().toEpochSecond())
                .endDate(productPrice.getEndDate() != null ? productPrice.getEndDate().toEpochSecond() : null)
                .note(productPrice.getNote())
                .active(productPrice.isActive())
                .modifiedDate(productPrice.getStartDate().toEpochSecond())
                .build();
    }

}
