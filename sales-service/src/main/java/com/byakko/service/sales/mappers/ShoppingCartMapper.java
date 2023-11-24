package com.byakko.service.sales.mappers;

import com.byakko.service.sales.dtos.shopping_cart.ShoppingCartItemResponse;
import com.byakko.service.sales.models.ShoppingCartItem;

import java.util.List;

public class ShoppingCartMapper {

    public static ShoppingCartItemResponse toShopCartItemResponse(ShoppingCartItem shoppingCartItem) {
        return ShoppingCartItemResponse.builder()
                .id(shoppingCartItem.getId())
                .product(shoppingCartItem.getProduct())
                .variant(shoppingCartItem.getVariant())
                .quantity(shoppingCartItem.getQuantity())
                .build();
    }

}
