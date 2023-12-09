package com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface EmployeeService {

    GetEmployeeAccountResponse getEmployeeAccount(@Valid GetEmployeeAccountCommand command);
    CreateEmployeeAccountResponse createEmployeeAccount(@Valid CreateEmployeeAccountCommand command);
    EmployeeSignInResponse signIn(@Valid EmployeeSignInCommand command);
//    void signOut(@Valid CustomerSignOutCommand command);
    void changePassword(@Valid EmployeeChangePasswordCommand command);
    void sendResetPasswordMail(@Valid EmployeeSendResetPasswordMailCommand command);
    void resetPassword(@Valid EmployeeResetPasswordCommand command);
    void deleteEmployeeAccount(@Valid DeleteEmployeeAccountCommand command);
}
