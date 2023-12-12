package com.byakko.service.product.mappers;

import com.byakko.common.DomainConstants;
import com.byakko.service.product.dtos.product.ProductMinResponse;
import com.byakko.service.product.dtos.product.ProductResponse;
import com.byakko.service.product.dtos.product.ProductVariantMinResponse;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductPrice;
import com.byakko.service.product.models.ProductVariant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        Map<String, BigDecimal> variantPriceMap = product.getProductPrices().stream()
                .filter(price -> {
                    LocalDate startDate = price.getStartDate();
                    LocalDate endDate = price.getEndDate();

                    // Kiểm tra xem currentTime có nằm trong khoảng startDate và endDate không
                    return currentTime.toLocalDate().compareTo(startDate) >= 0 &&
                            (endDate == null || currentTime.toLocalDate().compareTo(endDate) <= 0);
                })
                .collect(Collectors.toMap(
                        price -> price.getVariant().getId(),
                        ProductPrice::getPrice,
                        (existing, replacement) -> existing // Nếu có trùng lặp, giữ giá trị hiện tại
                ));

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
                                .peek(variant -> {
                                    BigDecimal price = variantPriceMap.get(variant.getId());
                                    variant.setPrice(price != null ? price : null);
                                })
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
                .price(variant.getPrice())
                .options(options)
                .build();
    }

}
