package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceEntity;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoryResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPrice;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;

public class ProductPriceMapper {

    public static ProductPrice toProductPriceHistory(ProductPriceEntity productPriceEntity) {
        return ProductPrice.Builder.builder()
                .product(new Product(new ProductId(productPriceEntity.getProduct().getId())))
                .startDate(productPriceEntity.getStartDate())
                .endDate(productPriceEntity.getEndDate())
                .originalPrice(productPriceEntity.getOriginalPrice())
                .price(productPriceEntity.getPrice())
                .pricePerItem(productPriceEntity.getPricePerItem())
                .reason(productPriceEntity.getReason())
                .build();
    }

    public static ProductPriceEntity toProductPriceHistoryEntity(ProductPrice productPrice) {
        return ProductPriceEntity.builder()
                .product(new ProductEntity(productPrice.getProduct().getId().getValue()))
                .startDate(productPrice.getStartDate())
                .endDate(productPrice.getEndDate())
                .originalPrice(productPrice.getOriginalPrice())
                .price(productPrice.getPrice())
                .pricePerItem(productPrice.getPricePerItem())
                .reason(productPrice.getReason())
                .build();
    }

    public static ProductPriceHistoryResponse toProductPriceHistoryResponse(ProductPriceEntity productPriceEntity) {
        return ProductPriceHistoryResponse.Builder.builder()
                .startDate(productPriceEntity.getStartDate().toEpochSecond())
                .endDate(productPriceEntity.getEndDate() != null ? productPriceEntity.getEndDate().toEpochSecond() : null)
                .originalPrice(productPriceEntity.getOriginalPrice() != null ? productPriceEntity.getOriginalPrice().getAmount() : null)
                .price(productPriceEntity.getPrice() != null ? productPriceEntity.getPrice().getAmount() : null)
                .pricePerItem(productPriceEntity.getPricePerItem() != null ? productPriceEntity.getPricePerItem().getAmount() : null)
                .reason(productPriceEntity.getReason())
                .build();
    }

}
