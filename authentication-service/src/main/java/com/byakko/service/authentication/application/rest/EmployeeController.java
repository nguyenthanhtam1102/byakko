package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerChangePasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.DeleteCustomerCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.SendResetPasswordMailCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.*;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getEmployeeAccount(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(employeeService.getEmployeeAccount(new GetEmployeeAccountCommand(userId)));
    }

    @PostMapping
    public ResponseEntity<?> createEmployeeAccount(@RequestBody CreateEmployeeAccountCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployeeAccount(command));
    }

//    @PutMapping("")
//    public ResponseEntity<?> updateEmployeeAccount() {
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeAccount(@PathVariable("id") String id) {
        employeeService.deleteEmployeeAccount(new DeleteEmployeeAccountCommand(id));
        return ResponseEntity.ok("Delete success");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody EmployeeSignInCommand command) {
        return ResponseEntity.ok(employeeService.signIn(command));
    }

    @PutMapping("/{userId}/changepassword")
    public ResponseEntity<?> changePassword(@PathVariable("userId") String id, @RequestBody EmployeeChangePasswordCommand command) {
        command.setId(id);
        employeeService.changePassword(command);
        return ResponseEntity.ok("Change password success");
    }

    @GetMapping("/sendResetPasswordMail")
    public ResponseEntity<?> sendResetPasswordMail(@ModelAttribute EmployeeSendResetPasswordMailCommand command) {
        employeeService.sendResetPasswordMail(command);
        return ResponseEntity.ok("Email send success");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(
            @RequestParam("token") String token,
            @RequestBody EmployeeResetPasswordCommand command) {
        command.setToken(token);
        employeeService.resetPassword(command);
        return ResponseEntity.ok("Reset password success");
    }

}
