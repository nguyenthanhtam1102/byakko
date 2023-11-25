package com.byakko.service.sales.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.sales.dtos.shopping_cart.*;
import com.byakko.service.sales.mappers.ShoppingCartMapper;
import com.byakko.service.sales.models.ShoppingCartItem;
import com.byakko.service.sales.repositories.ShoppingCartItemRepository;
import com.byakko.service.sales.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public Integer getTotalOfQuantity(GetTotalOfQuantityCommand command) {
        return shoppingCartItemRepository.getTotalQuantityInCart(command.getCustomerId());
    }

    @Override
    public List<ShoppingCartItemResponse> getShoppingCart(GetShoppingCartCommand command) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRepository.findAllByCustomerOrderByModifiedDateDesc(command.getCustomerId());
        return shoppingCartItems.stream().map(ShoppingCartMapper::toShopCartItemResponse).toList();
    }

    @Override
    public ShoppingCartItemResponse addItemToCart(AddItemToCartCommand command) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findShoppingCartItemByProductAndVariant(command.getProductId(), command.getVariantId()).orElse(null);

        if(shoppingCartItem != null) {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + 1);
        } else {
            shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setCustomer(command.getCustomerId());
            shoppingCartItem.setProduct(command.getProductId());
            shoppingCartItem.setVariant(command.getVariantId());
            shoppingCartItem.setQuantity(command.getQuantity());
        }

        shoppingCartItemRepository.save(shoppingCartItem);

        return ShoppingCartMapper.toShopCartItemResponse(shoppingCartItem);
    }

    @Override
    public ShoppingCartItemResponse updateItemFromCart(UpdateItemFromCartCommand command) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(command.getItemId())
                .orElseThrow(() -> new NotFoundException(String.format("Shopping cart with id %s not found", command.getItemId())));

        if(command.getVariantId() != null) {
            if(command.getVariantId().isBlank())
                throw new ValidationException(Map.of("variant_id", "variant_id must be not blank"));
            shoppingCartItem.setVariant(command.getVariantId());
        }

        if(command.getQuantity() != null) {
            if(command.getQuantity() <= 0)
                throw new ValidationException(Map.of("quantity", "quantity must be greater than 0"));
            shoppingCartItem.setQuantity(command.getQuantity());
        }

        shoppingCartItem.setModifiedDate(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        shoppingCartItemRepository.save(shoppingCartItem);

        return ShoppingCartMapper.toShopCartItemResponse(shoppingCartItem);
    }

    @Override
    public void deleteItemFromCart(DeleteItemFromCartCommand command) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(command.getItemId())
                .orElseThrow(() -> new NotFoundException(String.format("Shopping cart with id %s not found", command.getItemId())));
        shoppingCartItemRepository.delete(shoppingCartItem);
    }
}
