package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.AuthenticationApplicationService;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CustomerSignUpCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.signUp(command));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody CustomerSignInCommand command) {
        return ResponseEntity.ok(customerService.signIn(command));
    }

    @PutMapping("")
    public ResponseEntity<?> resetPassword(@PathVariable("") Long id, @RequestBody ) {

    }

    @PutMapping("")
    public ResponseEntity<?> changePassword(@PathVariable("") Long id, @RequestBody ) {

    }

    @GetMapping("")
    public ResponseEntity<?> sendVerifyEmail() {

    }

    @GetMapping("")
    public ResponseEntity<?> sendVerifyCode() {

    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteCustomer(@PathVariable("") Long id) {

    }

}
