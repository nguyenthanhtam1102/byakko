package com.byakko.service.sales.service.product.controllers;

import com.byakko.service.sales.service.product.dtos.product_price.CreateProductPriceCommand;
import com.byakko.service.sales.service.product.dtos.product_price.DeleteProductPriceCommand;
import com.byakko.service.sales.service.product.dtos.product_price.ListProductPricesCommand;
import com.byakko.service.sales.service.product.dtos.product_price.UpdateProductPriceCommand;
import com.byakko.service.sales.service.product.services.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @GetMapping("/{id}/prices")
    public ResponseEntity<?> listProductPrices(@PathVariable("id") String id,
                                               @ModelAttribute ListProductPricesCommand command) {
        command.setProductId(id);
        return ResponseEntity.ok(productPriceService.listProductPrices(command));
    }

    @PostMapping("/{id}/prices")
    public ResponseEntity<?> createProductPrice(@PathVariable("id") String id,
                                                @RequestBody CreateProductPriceCommand command) {
        command.setProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(productPriceService.createProductPrice(command));
    }

    @PutMapping("/{id}/prices/{price_id}")
    public ResponseEntity<?> updateProductPrice(@PathVariable("id") String productId,
                                                @PathVariable("price_id") String priceId,
                                                @RequestBody UpdateProductPriceCommand command) {
        command.setId(priceId);
        return ResponseEntity.ok(productPriceService.updateProductPrice(command));
    }

    @DeleteMapping("/{id}/prices/{price_id}")
    public ResponseEntity<?> deleteProductPrice(@PathVariable("id") String productId,
                                                @PathVariable("price_id") String priceId) {
        productPriceService.deleteProductPrice(new DeleteProductPriceCommand(priceId));
        return ResponseEntity.ok("Delete success");
    }

}
