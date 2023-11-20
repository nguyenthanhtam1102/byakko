package com.byakko.service.product.controllers;

import com.byakko.service.product.dtos.supplier.*;
import com.byakko.service.product.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> listAllSuppliers(@ModelAttribute ListAllSuppliersCommand command) {
        return ResponseEntity.ok(supplierService.listAllSuppliers(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable("id") String id) {
        return ResponseEntity.ok(supplierService.getSupplier(new GetSupplierCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody CreateSupplierCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.createSupplier(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") String id,
                                            @RequestBody UpdateSupplierCommand command) {
        command.setId(id);
        return ResponseEntity.ok(supplierService.updateSupplier(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") String id) {
        supplierService.deleteSupplier(new DeleteSupplierCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
