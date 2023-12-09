package com.byakko.service.sales.service.authentication.application.rest;

import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.SystemAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sa")
@RequiredArgsConstructor
public class SystemAdminController {

    private final SystemAdminService systemAdminService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SystemAdminSignInCommand command) {
        return ResponseEntity.ok(systemAdminService.signIn(command));
    }

}
