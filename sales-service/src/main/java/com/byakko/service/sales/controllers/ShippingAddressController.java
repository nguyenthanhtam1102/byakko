package com.byakko.service.sales.controllers;

import com.byakko.service.sales.dtos.shipping_address.*;
import com.byakko.service.sales.services.ShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/shipping-addresses")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @GetMapping("/{customer_id}")
    public ResponseEntity<?> listAllShippingAddresses(@PathVariable("customer_id") String customerId) {
        return ResponseEntity.ok(shippingAddressService.listAllShippingAddress(new ListAllShippingAddressCommand(customerId)));
    }

    @GetMapping("/{customer_id}/addresses/{address_id}")
    public ResponseEntity<?> getShippingAddress(@PathVariable("customer_id") String customerId,
                                                @PathVariable("address_id") String addressId) {
        return ResponseEntity.ok(shippingAddressService.getShippingAddress(new GetShippingAddressCommand(customerId, addressId)));
    }

    @PostMapping("/{customer_id}")
    public ResponseEntity<?> createShippingAddress(@PathVariable("customer_id") String customerId,
                                                   @RequestBody CreateShippingAddressCommand command) {
        command.setCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shippingAddressService.createShippingAddress(command));
    }

    @PutMapping("/{customer_id}/addresses/{address_id}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable("customer_id") String customerId,
                                                   @PathVariable("address_id") String addressId,
                                                   @RequestBody UpdateShippingAddressCommand command) {
        command.setCustomerId(customerId);
        command.setAddressId(addressId);
        return ResponseEntity.ok(shippingAddressService.updateShippingAddress(command));
    }

    @DeleteMapping("/{customer_id}/addresses/{address_id}")
    public ResponseEntity<?> deleteShippingAddress(@PathVariable("customer_id") String customerId,
                                                   @PathVariable("address_id") String addressId) {
        shippingAddressService.deleteShippingAddress(new DeleteShippingAddressCommand(customerId, addressId));
        return ResponseEntity.ok("Delete success");
    }

}
