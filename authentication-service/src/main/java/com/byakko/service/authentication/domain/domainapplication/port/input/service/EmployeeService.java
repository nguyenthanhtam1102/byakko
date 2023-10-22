package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface EmployeeService {

    EmployeeSignUpResponse signUp(@Valid EmployeeSignUpCommand command);
    EmployeeSignInResponse signIn(@Valid EmployeeSignInCommand command);

}
