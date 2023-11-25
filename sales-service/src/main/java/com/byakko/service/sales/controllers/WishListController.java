package com.byakko.service.sales.controllers;

import com.byakko.service.sales.dtos.wish_list_item.AddItemToWishListCommand;
import com.byakko.service.sales.dtos.wish_list_item.DeleteItemFromWishListCommand;
import com.byakko.service.sales.dtos.wish_list_item.GetWishListCommand;
import com.byakko.service.sales.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/wish-lists")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @GetMapping("/{customer_id}")
    public ResponseEntity<?> getWishList(@PathVariable("customer_id") String customerId) {
        return ResponseEntity.ok(wishListService.getWishList(new GetWishListCommand(customerId)));
    }

    @PostMapping("/{customer_id}")
    public ResponseEntity<?> addItemToWishList(@PathVariable("customer_id") String customerId,
                                               @RequestBody AddItemToWishListCommand command) {
        command.setCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListService.addItemToWishList(command));
    }

    @DeleteMapping("/{customer_id}/items/{item_id}")
    public ResponseEntity<?> deleteItemFromWishList(@PathVariable("customer_id") String customerId,
                                                    @PathVariable("item_id") String itemId) {
        wishListService.deleteItemFromWishList(new DeleteItemFromWishListCommand(customerId, itemId));
        return ResponseEntity.ok("Delete success");
    }

}
