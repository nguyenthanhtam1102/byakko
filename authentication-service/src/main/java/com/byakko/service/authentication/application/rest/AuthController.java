package com.byakko.service.authentication.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login() {

    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin() {

    }

}
