package com.byakko.service.product.mappers;

import com.byakko.common.DomainConstants;
import com.byakko.service.product.dtos.MoneyResponse;
import com.byakko.service.product.dtos.product_price.ProductPriceResponse;
import com.byakko.service.product.models.ProductPrice;

import java.time.ZoneId;

public class ProductPriceMapper {

    public static ProductPriceResponse toProductPriceResponse(ProductPrice productPrice) {
        return ProductPriceResponse.builder()
                .id(productPrice.getId())
                .employee(productPrice.getEmployee().getId())
                .price(MoneyResponse.toMoneyResponse(productPrice.getPrice()))
                .startDate(productPrice.getStartDate().atStartOfDay(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())
                .endDate(productPrice.getEndDate() != null
                        ? productPrice.getEndDate().atStartOfDay(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond()
                        : null)
                .note(productPrice.getNote())
                .active(productPrice.isActive())
                .modifiedDate(productPrice.getStartDate().atStartOfDay(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())
                .build();
    }

}
