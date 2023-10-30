package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceHistoryEntity;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoryResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPriceHistory;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;

public class ProductPriceHistoryMapper {

    public static ProductPriceHistory toProductPriceHistory(ProductPriceHistoryEntity productPriceHistoryEntity) {
        return ProductPriceHistory.Builder.builder()
                .product(new Product(new ProductId(productPriceHistoryEntity.getProduct().getId())))
                .startDate(productPriceHistoryEntity.getStartDate())
                .endDate(productPriceHistoryEntity.getEndDate())
                .originalPrice(productPriceHistoryEntity.getOriginalPrice())
                .price(productPriceHistoryEntity.getPrice())
                .pricePerItem(productPriceHistoryEntity.getPricePerItem())
                .reason(productPriceHistoryEntity.getReason())
                .build();
    }

    public static ProductPriceHistoryEntity toProductPriceHistoryEntity(ProductPriceHistory productPriceHistory) {
        return ProductPriceHistoryEntity.builder()
                .product(new ProductEntity(productPriceHistory.getProduct().getId().getValue()))
                .startDate(productPriceHistory.getStartDate())
                .endDate(productPriceHistory.getEndDate())
                .originalPrice(productPriceHistory.getOriginalPrice())
                .price(productPriceHistory.getPrice())
                .pricePerItem(productPriceHistory.getPricePerItem())
                .reason(productPriceHistory.getReason())
                .build();
    }

    public static ProductPriceHistoryResponse toProductPriceHistoryResponse(ProductPriceHistoryEntity productPriceHistoryEntity) {
        return ProductPriceHistoryResponse.Builder.builder()
                .startDate(productPriceHistoryEntity.getStartDate().toEpochSecond())
                .endDate(productPriceHistoryEntity.getEndDate() != null ? productPriceHistoryEntity.getEndDate().toEpochSecond() : null)
                .originalPrice(productPriceHistoryEntity.getOriginalPrice() != null ? productPriceHistoryEntity.getOriginalPrice().getAmount() : null)
                .price(productPriceHistoryEntity.getPrice() != null ? productPriceHistoryEntity.getPrice().getAmount() : null)
                .pricePerItem(productPriceHistoryEntity.getPricePerItem() != null ? productPriceHistoryEntity.getPricePerItem().getAmount() : null)
                .reason(productPriceHistoryEntity.getReason())
                .build();
    }

}
