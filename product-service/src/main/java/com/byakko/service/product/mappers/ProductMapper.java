package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.product.ProductMinResponse;
import com.byakko.service.product.dtos.product.ProductResponse;
import com.byakko.service.product.models.Product;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .description(product.getDescription())
                .categories(product.getCategories() != null
                        ? product.getCategories()
                                .stream()
                                .map(category -> CategoryMapper.toCategoryMinResponse(category, 0))
                                .toList()
                        : null)
                .build();
    }

    public static ProductMinResponse toProductMinResponse(Product product) {
        return ProductMinResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .barcode(product.getBarcode())
                .sku(product.getSku())
                .build();
    }

}
