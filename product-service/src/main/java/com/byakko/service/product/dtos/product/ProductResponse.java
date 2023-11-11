package com.byakko.service.product.dtos.product;

import com.byakko.service.product.dtos.category.CategoryMinResponse;
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
    private List<CategoryMinResponse> categories;

}
