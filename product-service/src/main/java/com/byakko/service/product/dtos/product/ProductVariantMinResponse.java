package com.byakko.service.product.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ProductVariantMinResponse {

    private String id;
    private Map<String, String> options;

}
