package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.ShopOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopowners")
@RequiredArgsConstructor
public class ShopOwnerController {

    private final ShopOwnerService shopOwnerService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody ShopOwnerSignInCommand command) {
        return ResponseEntity.ok(shopOwnerService.signIn(command));
    }

    @PostMapping
    public ResponseEntity<?> createShopOwner(@RequestBody ShopOwnerSignUpCommand command) {
        return ResponseEntity.ok(shopOwnerService.signUp(command));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateShopOwner(@PathVariable("id") String id) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteShopOwner(@PathVariable("id") String id) {
//
//    }

}
