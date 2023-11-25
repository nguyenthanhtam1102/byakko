package com.byakko.service.sales.dtos.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShoppingCartItemResponse {

    private String id;
    private String product;
    private String variant;
    private Integer quantity;

}
