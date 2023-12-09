package com.byakko.service.sales.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.CustomerService;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CustomerSignUpCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.signUp(command));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody CustomerSignInCommand command) {
        return ResponseEntity.ok(customerService.signIn(command));
    }

    @PostMapping("/{userId}/signout")
    public ResponseEntity<?> signOut(@PathVariable("userId") String userId) {
        customerService.signOut(new CustomerSignOutCommand(userId));
        return ResponseEntity.ok("singout success");
    }

    @GetMapping("/sendResetPasswordMail")
    public ResponseEntity<?> sendResetPasswordMail(@ModelAttribute SendResetPasswordMailCommand command) {
        customerService.sendResetPasswordMail(command);
        return ResponseEntity.ok("Email send success");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody CustomerResetPasswordCommand command) {
        System.out.println(token);
        command.setToken(token);
        customerService.resetPassword(command);
        return ResponseEntity.ok("Reset password success");
    }

    @PutMapping("/{id}/changepassword")
    public ResponseEntity<?> changePassword(@PathVariable("id") String id, @RequestBody CustomerChangePasswordCommand command) {
        command.setId(id);
        customerService.changePassword(command);
        return ResponseEntity.ok("Change password success");
    }

    @GetMapping("/{id}/resendVerifyMail")
    public ResponseEntity<?> resendVerifyMail(@PathVariable("id") String id) {
        customerService.resendEmailAddressVerificationMail(new ResendEmailAddressVerificationMailCommand(id));
        return ResponseEntity.ok("Email address verification mail send success");
    }

    @GetMapping("/verifyemailaddress")
    public ResponseEntity<?> verifyEmailAddress(@RequestParam("token") String token) {
        customerService.verifyEmailAddress(new VerifyEmailAddressCommand(token));
        return ResponseEntity.ok("Verify email address success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(new DeleteCustomerCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
