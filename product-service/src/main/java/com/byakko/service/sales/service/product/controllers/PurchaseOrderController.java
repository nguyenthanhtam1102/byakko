package com.byakko.service.sales.service.product.controllers;

import com.byakko.service.product.dtos.purchase_order.*;
import com.byakko.service.sales.service.product.services.PurchaseOrderService;
import com.byakko.service.sales.service.product.dtos.purchase_order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase_orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<?> listAllPurchaseOrders(@ModelAttribute ListAllPurchaseOrderCommand command) {
        return ResponseEntity.ok(purchaseOrderService.listAllPurchaseOrder(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchaseOrder(@PathVariable("id") String id) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrder(new GetPurchaseOrderCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createPurchaseOrder(@RequestBody CreatePurchaseOrderCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.createPurchaseOrder(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable("id") String id,
                                                 @RequestBody UpdatePurchaseOrderCommand command) {
        return ResponseEntity.ok(purchaseOrderService.updateOrderResponse(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchaseOrder(@PathVariable("id") String id) {
        purchaseOrderService.deletePurchaseOrder(new DeletePurchaseOrderCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
