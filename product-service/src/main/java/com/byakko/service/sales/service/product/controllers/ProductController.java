package com.byakko.service.sales.service.product.controllers;

import com.byakko.service.product.dtos.product.*;
import com.byakko.service.sales.service.product.services.ProductService;
import com.byakko.service.sales.service.product.dtos.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> listAllProducts(@ModelAttribute ListAllProductsCommand command) {
        return ResponseEntity.ok(productService.listAllProducts(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProduct(new GetProductCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,
                                           @RequestBody UpdateProductCommand command) {
        command.setId(id);
        return ResponseEntity.ok(productService.updateProduct(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(new DeleteProductCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
