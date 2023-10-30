package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.AssetEntity;
import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductItemResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterItemResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;

import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toProduct(ProductEntity productEntity) {
        return Product.Builder.builder()
                .id(new ProductId(productEntity.getId()))
                .barcode(productEntity.getBarcode())
                .sku(productEntity.getSku())
                .slug(productEntity.getSlug())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .originalPrice(productEntity.getOriginalPrice())
                .price(productEntity.getPrice())
                .pricePerItem(productEntity.getPricePerItem())
                .status(productEntity.getStatus())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .image(productEntity.getImage() != null ? AssetMapper.toAsset(productEntity.getImage()) : null)
                .assets(productEntity.getAssets() != null
                        ? productEntity.getAssets().stream().map(AssetMapper::toAsset).collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId().getValue())
                .barcode(product.getBarcode())
                .sku(product.getSku())
                .slug(product.getSlug())
                .name(product.getName())
                .description(product.getDescription())
                .originalPrice(product.getOriginalPrice())
                .price(product.getPrice())
                .pricePerItem(product.getPricePerItem())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .image(product.getImage() != null ? new AssetEntity(product.getImage().getId().getValue()) : null)
                .assets(product.getAssets() != null
                        ? product.getAssets()
                        .stream()
                        .map(asset -> new AssetEntity(asset.getId().getValue()))
                        .collect(Collectors.toSet())
                        : null)
                .relatedProducts(product.getRelatedProducts()
                        .stream()
                        .map(productId -> new ProductEntity(productId.getId().getValue()))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static ProductItemResponse toProductItemResponse(ProductEntity productEntity) {
        return ProductItemResponse.Builder.builder()
                .id(productEntity.getId())
                .slug(productEntity.getSlug())
                .name(productEntity.getName())
                .originalPrice(productEntity.getOriginalPrice() != null ? productEntity.getOriginalPrice().getAmount() : null)
                .price(productEntity.getPrice() != null ? productEntity.getPrice().getAmount() : null)
                .createdAt(productEntity.getCreatedAt().toEpochSecond())
                .updatedAt(productEntity.getUpdatedAt() != null ? productEntity.getUpdatedAt().toEpochSecond() : null)
                .image(productEntity.getImage() != null ? AssetMapper.toAssetResponse(productEntity.getImage()) : null)
                .build();
    }

    public static ProductFilterItemResponse toProductFilterItemResponse(ProductEntity product) {
        return ProductFilterItemResponse.Builder.builder()
                .id(product.getId())
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
