package com.byakko.service.sales.controllers;

import com.byakko.service.sales.dtos.shopping_cart.*;
import com.byakko.service.sales.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{customer_id}/quantity")
    public ResponseEntity<?> getTotalOfQuantity(@PathVariable("customer_id") String customerId) {
        return ResponseEntity.ok(shoppingCartService.getTotalOfQuantity(new GetTotalOfQuantityCommand(customerId)));
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<?> getShoppingCart(@PathVariable("customer_id") String customerId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(new GetShoppingCartCommand(customerId)));
    }

    @PostMapping("/{customer_id}")
    public ResponseEntity<?> addItemToCart(@PathVariable("customer_id") String customerId, @RequestBody AddItemToCartCommand command) {
        command.setCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.addItemToCart(command));
    }

    @PutMapping("/{customer_id}/items/{item_id}")
    public ResponseEntity<?> updateItemFromCart(@PathVariable("customer_id") String customerId,
                                                @PathVariable("item_id") String itemId,
                                                @RequestBody UpdateItemFromCartCommand command) {
        command.setCustomerId(customerId);
        command.setItemId(itemId);
        return ResponseEntity.ok(shoppingCartService.updateItemFromCart(command));
    }
    @DeleteMapping("/{customer_id}/items/{item_id}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable("customer_id") String customerId, @PathVariable("item_id") String itemId) {
        shoppingCartService.deleteItemFromCart(new DeleteItemFromCartCommand(customerId, itemId));
        return ResponseEntity.ok("Delete success");
    }

}
