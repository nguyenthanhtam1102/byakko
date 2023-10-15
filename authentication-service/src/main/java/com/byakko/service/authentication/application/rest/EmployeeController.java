package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.AuthenticationApplicationService;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody EmployeeSignUpCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.signUp(command));
    }

    @PostMapping("")
    public ResponseEntity<?> signIn(@RequestBody EmployeeSignInCommand command) {
        return ResponseEntity.ok(employeeService.signIn(command));
    }

    @PutMapping("")
    public ResponseEntity<?> resetPassword(@PathVariable("") Long id, ) {

    }

    @PostMapping("")
    public ResponseEntity<?> setRoleToEmployee() {

    }

}
