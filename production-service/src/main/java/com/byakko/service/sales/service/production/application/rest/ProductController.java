package com.byakko.service.sales.service.production.application.rest;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.GetProductDetailCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.port.input.ProductService;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ADMIN
    @GetMapping
    public ResponseEntity<?> listAllProducts(@ModelAttribute ListAllProductCommand command) {
        return ResponseEntity.ok(productService.listAllProducts(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProduct(new GetProductCommand(id)));
    }

    @PostMapping
    private ResponseEntity<?> createProduct(@RequestBody CreateProductCommand command) {
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

    @GetMapping("/{id}/price-histories")
    public ResponseEntity<?> getPriceHistoriesForProduct(@PathVariable("id") String id,
                                                         @ModelAttribute GetPriceHistoriesForProductCommand command) {
        command.setId(id);
        return ResponseEntity.ok(productService.getPriceHistoriesForProduct(command));
    }

    // CUSTOMER
    @GetMapping("/product-filter")
    private ResponseEntity<?> filterProducts(@ModelAttribute ProductFilterCommand command) {
        return ResponseEntity.ok(productService.filterProduct(command));
    }

    @GetMapping("/{id}/product-details")
    private ResponseEntity<?> getProductDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProductDetail(new GetProductDetailCommand(id)));
    }

}
