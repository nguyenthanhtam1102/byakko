package com.byakko.service.sales.service.product.dtos.product;

import com.byakko.service.sales.service.product.dtos.assets.AssetMinResponse;
import com.byakko.service.sales.service.product.dtos.category.CategoryMinResponse;
import com.byakko.service.sales.service.product.dtos.global_option.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String id;
    private String barcode;
    private String name;
    private String sku;
    private String description;
    private List<AssetMinResponse> assets;
    private List<CategoryMinResponse> categories;
    private List<OptionResponse> options;
    private List<ProductVariantMinResponse> variants;

}
