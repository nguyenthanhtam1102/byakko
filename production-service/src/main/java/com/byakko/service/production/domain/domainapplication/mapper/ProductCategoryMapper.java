package com.byakko.service.production.domain.domainapplication.mapper;

import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryChildResponse;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryResponse;
import com.byakko.service.production.domain.domaincore.entity.ProductCategory;

public class ProductCategoryMapper {

    public static ProductCategoryResponse toProductCategoryResponse(ProductCategory category) {
        return ProductCategoryResponse.Builder.builder()
                .id(category.getId().getValue())
                .name(category.getName())
                .parent(category.getParent() != null ? category.getParent().getId().getValue() : null)
                .children(category.getChildren() != null
                        ? category.getChildren()
                                .stream()
                                .map(ProductCategoryMapper::toProductCategoryChildResponse)
                                .toList()
                        : null)
                .createdAt(category.getCreatedAt().toEpochSecond())
                .updatedAt(category.getUpdatedAt() != null ? category.getUpdatedAt().toEpochSecond() : null)
                .build();
    }

    public static ProductCategoryChildResponse toProductCategoryChildResponse(ProductCategory category) {
        return ProductCategoryChildResponse.Builder.builder()
                .id(category.getId().getValue())
                .name(category.getName())
                .children(category.getChildren() != null
                        ? category.getChildren()
                                .stream()
                                .map(ProductCategoryMapper::toProductCategoryChildResponse)
                                .toList()
                        : null)
                .build();
    }

}
