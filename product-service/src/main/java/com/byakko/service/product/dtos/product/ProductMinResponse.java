package com.byakko.service.product.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductMinResponse {

    private String id;
    private String barcode;
    private String name;
    private String sku;

}
