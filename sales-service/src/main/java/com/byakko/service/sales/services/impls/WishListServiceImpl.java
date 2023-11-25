package com.byakko.service.sales.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.sales.dtos.wish_list_item.*;
import com.byakko.service.sales.mappers.WishListMapper;
import com.byakko.service.sales.models.WishListItem;
import com.byakko.service.sales.repositories.WishListItemRepository;
import com.byakko.service.sales.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListItemRepository wishListItemRepository;

    @Override
    public WishListResponse getWishList(GetWishListCommand command) {
        Pageable pageable = PageRequest.of(
                command.getPage(), command.getLimit(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<WishListItem> page = wishListItemRepository.findAll(pageable);

        return WishListResponse.builder()
                .data(page.get().map(WishListMapper::toWishListItemResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public WishListItemResponse addItemToWishList(AddItemToWishListCommand command) {
        WishListItem wishListItem = new WishListItem();
        wishListItem.setCustomer(command.getCustomerId());
        wishListItem.setProduct(command.getProductId());

        wishListItemRepository.save(wishListItem);

        return WishListMapper.toWishListItemResponse(wishListItem);
    }

    @Override
    public void deleteItemFromWishList(DeleteItemFromWishListCommand command) {
        WishListItem wishListItem = wishListItemRepository.findById(command.getItemId())
                .orElseThrow(() -> new NotFoundException(String.format("Wish list item with id %s not found", command.getItemId())));
        wishListItemRepository.delete(wishListItem);
    }
}
