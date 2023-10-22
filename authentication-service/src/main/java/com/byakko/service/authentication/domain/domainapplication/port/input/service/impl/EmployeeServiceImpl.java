package com.byakko.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.handler.employee.EmployeeSignInCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.handler.employee.EmployeeSignUpCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeSignUpCommandHandler employeeSignUpCommandHandler;
    private final EmployeeSignInCommandHandler employeeSignInCommandHandler;

    public EmployeeServiceImpl(EmployeeSignUpCommandHandler employeeSignUpCommandHandler,
                               EmployeeSignInCommandHandler employeeSignInCommandHandler) {
        this.employeeSignUpCommandHandler = employeeSignUpCommandHandler;
        this.employeeSignInCommandHandler = employeeSignInCommandHandler;
    }

    @Override
    public EmployeeSignUpResponse signUp(EmployeeSignUpCommand command) {
        return employeeSignUpCommandHandler.signUp(command);
    }

    @Override
    public EmployeeSignInResponse signIn(EmployeeSignInCommand command) {
        return employeeSignInCommandHandler.signIn(command);
    }

}
