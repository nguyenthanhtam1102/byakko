package com.byakko.service.product.controllers;

import com.byakko.service.product.dtos.inventory_count_sheet.*;
import com.byakko.service.product.services.InventoryCountSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory_count_sheets")
@RequiredArgsConstructor
public class InventoryCountSheetController {

    private final InventoryCountSheetService inventoryCountSheetService;

    @GetMapping
    public ResponseEntity<?> listAllInventoryCountSheets(@ModelAttribute ListAllInventoryCountSheetCommand command) {
        return ResponseEntity.ok(inventoryCountSheetService.listAllInventoryCountSheet(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryCountSheet(@PathVariable("id") String id) {
        return ResponseEntity.ok(inventoryCountSheetService.getInventoryCountSheet(new GetInventoryCountSheetCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createInventoryCountSheet(@RequestBody CreateInventoryCountSheetCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryCountSheetService.createInventoryCountSheet(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventoryCountSheet(@PathVariable("id") String id,
                                                       @RequestBody UpdateInventoryCountSheetCommand command) {
        command.setId(id);
        return ResponseEntity.ok(inventoryCountSheetService.updateInventoryCountSheet(command));
    }

    @PutMapping("/{id}/count")
    public ResponseEntity<?> inventoryCount(@PathVariable("id") String id, @RequestBody InventoryCountCommand command) {
        command.setInventoryCountSheet(id);
        return ResponseEntity.ok(inventoryCountSheetService.inventoryCount(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventoryCountSheet(@PathVariable("id") String id) {
        inventoryCountSheetService.deleteInventoryCountSheet(new DeleteInventoryCountSheetCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
