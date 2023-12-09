package com.byakko.service.sales.service.product.mappers;

import com.byakko.service.sales.service.product.dtos.product.ProductMinResponse;
import com.byakko.service.sales.service.product.dtos.product.ProductResponse;
import com.byakko.service.sales.service.product.dtos.product.ProductVariantMinResponse;
import com.byakko.service.sales.service.product.models.Product;
import com.byakko.service.sales.service.product.models.ProductVariant;

import java.util.HashMap;
import java.util.Map;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .description(product.getDescription())
                .assets(product.getAssets() != null
                        ? product.getAssets()
                                .stream()
                                .map(AssetMapper::toAssetMinResponse)
                                .toList()
                        : null)
                .categories(product.getCategories() != null
                        ? product.getCategories()
                                .stream()
                                .filter(category -> !category.isDeleted())
                                .map(category -> CategoryMapper.toCategoryMinResponse(category, 0))
                                .toList()
                        : null)
                .options(product.getOptions() != null
                        ? product.getOptions()
                                .stream()
                                .filter(option -> !option.isDeleted())
                                .map(OptionMapper::toOptionResponse)
                                .toList()
                        : null)
                .variants(product.getVariants() != null
                        ? product.getVariants()
                                .stream()
                                .filter(variant -> !variant.isDeleted())
                                .map(ProductMapper::toProductVariantMinResponse)
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
                .image(product.getAssets() != null && !product.getAssets().isEmpty()
                        ? AssetMapper.toAssetMinResponse(product.getAssets().stream().findFirst().get())
                        : null)
                .build();
    }

    public static ProductVariantMinResponse toProductVariantMinResponse(ProductVariant variant) {
        Map<String, String> options;
        if(variant.getVariantOptions() != null && !variant.getVariantOptions().isEmpty()) {
            options = new HashMap<>();
            variant.getVariantOptions().forEach(variantOption -> {
                options.put(variantOption.getOption().getId(), variantOption.getValue().getId());
            });
        } else {
            options = null;
        }

        return ProductVariantMinResponse.builder()
                .id(variant.getId())
                .options(options)
                .build();
    }

}
