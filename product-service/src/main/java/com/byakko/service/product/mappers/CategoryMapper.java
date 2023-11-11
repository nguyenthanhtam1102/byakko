package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.category.CategoryMinResponse;
import com.byakko.service.product.dtos.category.CategoryResponse;
import com.byakko.service.product.models.Category;

public class CategoryMapper {

    public static CategoryResponse toCategoryResponse(Category category, int depth) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parent(category.getParent() != null ? toCategoryMinResponse(category.getParent(), 0) : null)
                .children(depth > 0 && category.getChildren() != null && !category.getChildren().isEmpty()
                        ? category.getChildren().stream().map(child -> toCategoryMinResponse(child, depth - 1)).toList()
                        : null)
                .createdAt(category.getCreatedAt().toEpochSecond())
                .updatedAt(category.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static CategoryMinResponse toCategoryMinResponse(Category category, int depth) {
        return CategoryMinResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .children(depth > 0 && category.getChildren() != null && !category.getChildren().isEmpty()
                        ? category.getChildren().stream().map(child -> toCategoryMinResponse(child, depth - 1)).toList()
                        : null)
                .build();
    }

}
