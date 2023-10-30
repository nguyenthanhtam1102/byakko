package com.byakko.service.production.domain.domainapplication.mapper;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductItemResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductDetailResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterItemResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;

import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.Builder.builder()
                .id(product.getId().getValue())
                .barcode(product.getBarcode())
                .sku(product.getSku())
                .slug(product.getSlug())
                .name(product.getName())
                .description(product.getDescription())
                .originalPrice(product.getOriginalPrice() != null ? product.getOriginalPrice().getAmount() : null)
                .price(product.getPrice() != null ? product.getPrice().getAmount() : null)
                .pricePerItem(product.getPricePerItem() != null ? product.getPricePerItem().getAmount() : null)
                .createdAt(product.getCreatedAt().toEpochSecond())
                .updatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt().toEpochSecond() : null)
                .assets(product.getAssets() != null ? product.getAssets().stream().map(AssetMapper::toAssetResponse).collect(Collectors.toSet()) : null)
                .relatedProducts(product.getRelatedProducts() != null
                        ? product.getRelatedProducts()
                                .stream()
                                .map(ProductMapper::toProductItemResponse)
                                .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static ProductItemResponse toProductItemResponse(Product product) {
        return ProductItemResponse.Builder.builder()
                .id(product.getId().getValue())
                .slug(product.getSlug())
                .name(product.getName())
                .originalPrice(product.getOriginalPrice() != null ? product.getOriginalPrice().getAmount() : null)
                .price(product.getPrice() != null ? product.getPrice().getAmount() : null)
                .createdAt(product.getCreatedAt().toEpochSecond())
                .updatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt().toEpochSecond() : null)
                .image(product.getImage() != null ? AssetMapper.toAssetResponse(product.getImage()) : null)
                .build();
    }

    public static ProductDetailResponse toProductDetailResponse(Product product) {
        return ProductDetailResponse.Builder.builder()
                .id(product.getId().getValue())
                .slug(product.getSlug())
                .name(product.getName())
                .description(product.getDescription())
                .originalPrice(product.getOriginalPrice() != null ? product.getOriginalPrice().getAmount() : null)
                .price(product.getPrice() != null ? product.getPrice().getAmount() : null)
                .createdAt(product.getCreatedAt().toEpochSecond())
                .updatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt().toEpochSecond() : null)
                .assets(product.getAssets() != null
                        ? product.getAssets()
                                .stream()
                                .map(AssetMapper::toAssetResponse)
                                .collect(Collectors.toSet())
                        : null)
                .relatedProducts(product.getRelatedProducts() != null
                        ? product.getRelatedProducts()
                                .stream()
                                .map(ProductMapper::toProductFilterItemResponse)
                                .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static ProductFilterItemResponse toProductFilterItemResponse(Product product) {
        return ProductFilterItemResponse.Builder.builder()
                .id(product.getId().getValue())
                .slug(product.getSlug())
                .name(product.getName())
                .originalPrice(product.getOriginalPrice() != null ? product.getOriginalPrice().getAmount() : null)
                .price(product.getPrice() != null ? product.getPrice().getAmount() : null)
                .createdAt(product.getCreatedAt().toEpochSecond())
                .updatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt().toEpochSecond() : null)
                .image(product.getImage() != null ? AssetMapper.toAssetResponse(product.getImage()) : null)
                .build();
    }

}
