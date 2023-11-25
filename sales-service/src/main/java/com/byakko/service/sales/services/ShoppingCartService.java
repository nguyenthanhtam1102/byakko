package com.byakko.service.sales.services;

import com.byakko.service.sales.dtos.shopping_cart.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public interface ShoppingCartService {

    Integer getTotalOfQuantity(@Valid GetTotalOfQuantityCommand command);
    List<ShoppingCartItemResponse> getShoppingCart(@Valid GetShoppingCartCommand command);
    ShoppingCartItemResponse addItemToCart(@Valid AddItemToCartCommand command);;
    ShoppingCartItemResponse updateItemFromCart(@Valid UpdateItemFromCartCommand command);
    void deleteItemFromCart(@Valid DeleteItemFromCartCommand command);

}
