package com.byakko.service.sales.service.production.domain.domainapplication.mapper;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ProductVariantResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductVariant;

import java.util.HashMap;
import java.util.Map;

public class ProductVariantMapper {

    public static ProductVariantResponse toProductVariantResponse(ProductVariant variant) {
        ProductVariantResponse response = new ProductVariantResponse();
        response.setId(variant.getId().getValue());
        response.setSku(variant.getSku());

        Map<String, String> variantOptions = new HashMap<>();
        variant.getVariantOptions().forEach(vo -> variantOptions.put(vo.getOption().getId().getValue(), vo.getOptionValue().getId().getValue()));
        response.setOptions(variantOptions);

        return response;
    }

}
