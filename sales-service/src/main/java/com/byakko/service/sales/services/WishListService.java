package com.byakko.service.sales.services;

import com.byakko.service.sales.dtos.wish_list_item.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public interface WishListService {

    WishListResponse getWishList(@Valid GetWishListCommand command);
    WishListItemResponse addItemToWishList(@Valid AddItemToWishListCommand command);
    void deleteItemFromWishList(@Valid DeleteItemFromWishListCommand command);

}
