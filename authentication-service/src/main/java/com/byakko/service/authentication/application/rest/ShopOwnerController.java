package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.*;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.ShopOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/shopowners")
@RequiredArgsConstructor
public class ShopOwnerController {

    private final ShopOwnerService shopOwnerService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody ShopOwnerSignInCommand command) {
        return ResponseEntity.ok(shopOwnerService.signIn(command));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody ShopOwnerSignUpCommand command) {
        return ResponseEntity.ok(shopOwnerService.signUp(command));
    }

    @GetMapping("/sendResetPasswordMail")
    public ResponseEntity<?> sendResetPasswordMail(@ModelAttribute SendResetPasswordMailCommand command) {
        shopOwnerService.sendResetPasswordMail(command);
        return ResponseEntity.ok("Email send success");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody ShopOwnerResetPasswordCommand command) {
        System.out.println(token);
        command.setToken(token);
        shopOwnerService.resetPassword(command);
        return ResponseEntity.ok("Reset password success");
    }

    @PutMapping("/{id}/changepassword")
    public ResponseEntity<?> changePassword(@PathVariable("id") String id, @RequestBody ShopOwnerChangePasswordCommand command) {
        command.setId(id);
        shopOwnerService.changePassword(command);
        return ResponseEntity.ok("Change password success");
    }

    @GetMapping("/{id}/resendVerifyMail")
    public ResponseEntity<?> resendVerifyMail(@PathVariable("id") String id) {
        shopOwnerService.resendEmailAddressVerificationMail(new ResendEmailAddressVerificationMailCommand(id));
        return ResponseEntity.ok("Email address verification mail send success");
    }

    @GetMapping("/verifyemailaddress")
    public ResponseEntity<?> verifyEmailAddress(@RequestParam("token") String token) {
        shopOwnerService.verifyEmailAddress(new VerifyEmailAddressCommand(token));
        return ResponseEntity.ok("Verify email address success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
        shopOwnerService.deleteCustomer(new DeleteShopOwner(id));
        return ResponseEntity.ok("Delete success");
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
