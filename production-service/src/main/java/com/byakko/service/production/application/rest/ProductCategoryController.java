package com.byakko.service.production.application.rest;

import com.byakko.service.production.domain.domainapplication.dto.category.*;
import com.byakko.service.production.domain.domainapplication.port.input.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<?> listAllProductCategories(@ModelAttribute ListAllProductCategoryCommand command) {
        return ResponseEntity.ok(productCategoryService.listAllProductCategories(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCategory(@PathVariable("id") String id,
                                                @RequestParam(value = "depth", defaultValue = "0") Integer depth) {
        return ResponseEntity.ok(productCategoryService.getProductCategory(new GetProductCategoryCommand(id, depth)));
    }

    @PostMapping
    public ResponseEntity<?> createProductCategory(@RequestBody CreateProductCategoryCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.createProductCategory(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductCategory(@PathVariable("id") String id,
                                                   @RequestBody UpdateProductCategoryCommand command) {
        command.setId(id);
        return ResponseEntity.ok(productCategoryService.updateProductCategory(command));
    }

    // Handle child
    // 0: Set all child with parent null
    // 1: Set all child up to parent level
    // 2: Delete all level child
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable("id") String id,
                                                   @RequestParam(value = "handle_child", defaultValue = "0") int handleChild) {
        productCategoryService.deleteProductCategory(new DeleteProductCategoryCommand(id, handleChild));
        return ResponseEntity.ok("Delete success");
    }

}
