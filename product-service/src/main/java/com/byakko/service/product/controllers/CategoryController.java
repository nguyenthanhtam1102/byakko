package com.byakko.service.product.controllers;

import com.byakko.service.product.dtos.category.*;
import com.byakko.service.product.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> listAllCategories(@ModelAttribute ListAllCategoriesCommand command) {
        return ResponseEntity.ok(categoryService.listAllCategories(command));
    }

    @GetMapping("/root")
    public ResponseEntity<?> getRootCategories(@ModelAttribute GetRootCategoriesCommand command) {
        return ResponseEntity.ok(categoryService.getRootCategories(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id,
                                         @RequestParam(value = "depth", defaultValue = "0") int depth) {
        return ResponseEntity.ok(categoryService.getCategory(new GetCategoryCommand(id, depth)));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @RequestBody UpdateCategoryCommand command) {
        command.setId(id);
        return ResponseEntity.ok(categoryService.updateCategory(command));
    }

    // delete option
    // 0: set toàn bộ children có parent_id = null
    // 1: thay thế. Ví dụ: ban đầu all > nam > áo. Sau khi xóa "nam" => all > áo
    // 2: xóa tất cả children
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id,
                                            @RequestParam(value = "delete_option", defaultValue = "0") int deleteOption) {
        categoryService.deleteCategory(new DeleteCategoryCommand(id, deleteOption));
        return ResponseEntity.ok("Delete success");
    }

}
