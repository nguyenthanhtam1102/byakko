package com.byakko.service.sales.service.production.dataaccess.mapper;

import com.byakko.service.sales.service.production.dataaccess.entity.ProductCategoryEntity;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ProductCategoryChildResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ProductCategoryResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;

import java.util.stream.Collectors;

public class ProductCategoryMapper {

    public static ProductCategory toProductCategory(ProductCategoryEntity productCategoryEntity) {
        return ProductCategory.Builder.builder()
                .id(new ProductCategoryId(productCategoryEntity.getId()))
                .name(productCategoryEntity.getName())
                .parent(productCategoryEntity.getParent() != null
                        ? new ProductCategory(new ProductCategoryId(productCategoryEntity.getId()))
                        : null)
                .children(productCategoryEntity.getChildren() != null
                        ? productCategoryEntity.getChildren()
                                .stream()
                                .map(ProductCategoryMapper::toProductCategory)
                                .collect(Collectors.toSet())
                        : null)
                .createdAt(productCategoryEntity.getCreatedAt())
                .updatedAt(productCategoryEntity.getUpdatedAt())
                .build();
    }

    public static ProductCategoryEntity toProductCategoryEntity(ProductCategory productCategory) {
        return ProductCategoryEntity.builder()
                .id(productCategory.getId().getValue())
                .name(productCategory.getName())
                .parent(productCategory.getParent() != null ? new ProductCategoryEntity(productCategory.getParent().getId().getValue()) : null)
                .children(productCategory.getChildren() != null
                        ? productCategory.getChildren()
                                .stream()
                                .map(children -> {
                                    ProductCategoryEntity childCategoryEntity = new ProductCategoryEntity(children.getId().getValue());
                                    childCategoryEntity.setParent(new ProductCategoryEntity(productCategory.getId().getValue()));
                                    return childCategoryEntity;
                                })
                                .collect(Collectors.toSet())
                        : null)
                .createdAt(productCategory.getCreatedAt())
                .updatedAt(productCategory.getUpdatedAt())
                .build();
    }

    public static ProductCategoryResponse toProductCategoryResponse(ProductCategoryEntity category) {
        return ProductCategoryResponse.Builder.builder()
                .id(category.getId())
                .name(category.getName())
                .parent(category.getParent() != null ? category.getParent().getId() : null)
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

    public static ProductCategoryChildResponse toProductCategoryChildResponse(ProductCategoryEntity category) {
        return ProductCategoryChildResponse.Builder.builder()
                .id(category.getId())
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
