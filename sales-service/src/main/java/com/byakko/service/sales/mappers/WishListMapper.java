package com.byakko.service.sales.mappers;

import com.byakko.service.sales.dtos.wish_list_item.WishListItemResponse;
import com.byakko.service.sales.models.WishListItem;

public class WishListMapper {

    public static WishListItemResponse toWishListItemResponse(WishListItem wishListItem) {
        return WishListItemResponse.builder()
                .customerId(wishListItem.getCustomer())
                .itemId(wishListItem.getId())
                .productId(wishListItem.getProduct())
                .build();
    }

}
