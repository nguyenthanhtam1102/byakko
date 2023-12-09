package com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.*;
import com.byakko.service.authentication.domain.domainapplication.handler.employee.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.EmployeeService;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.handler.employee.*;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final GetEmployeeAccountCommandHandler getEmployeeAccountCommandHandler;
    private final CreateEmployeeAccountCommandHandler createEmployeeAccountCommandHandler;
    private final EmployeeSignInCommandHandler employeeSignInCommandHandler;
    private final EmployeeChangePasswordCommandHandler employeeChangePasswordCommandHandler;
    private final EmployeeSendResetPasswordMailCommandHandler employeeSendResetPasswordMailCommandHandler;
    private final EmployeeResetPasswordCommandHandler employeeResetPasswordCommandHandler;
    private final DeleteEmployeeAccountCommandHandler deleteEmployeeAccountCommandHandler;

    public EmployeeServiceImpl(GetEmployeeAccountCommandHandler getEmployeeAccountCommandHandler,
                               CreateEmployeeAccountCommandHandler createEmployeeAccountCommandHandler,
                               EmployeeSignInCommandHandler employeeSignInCommandHandler,
                               EmployeeChangePasswordCommandHandler employeeChangePasswordCommandHandler,
                               EmployeeSendResetPasswordMailCommandHandler employeeSendResetPasswordMailCommandHandler,
                               EmployeeResetPasswordCommandHandler employeeResetPasswordCommandHandler,
                               DeleteEmployeeAccountCommandHandler deleteEmployeeAccountCommandHandler) {
        this.getEmployeeAccountCommandHandler = getEmployeeAccountCommandHandler;
        this.createEmployeeAccountCommandHandler = createEmployeeAccountCommandHandler;
        this.employeeSignInCommandHandler = employeeSignInCommandHandler;
        this.employeeChangePasswordCommandHandler = employeeChangePasswordCommandHandler;
        this.employeeSendResetPasswordMailCommandHandler = employeeSendResetPasswordMailCommandHandler;
        this.employeeResetPasswordCommandHandler = employeeResetPasswordCommandHandler;
        this.deleteEmployeeAccountCommandHandler = deleteEmployeeAccountCommandHandler;
    }

    @Override
    public GetEmployeeAccountResponse getEmployeeAccount(GetEmployeeAccountCommand command) {
        return getEmployeeAccountCommandHandler.handler(command);
    }

    @Override
    public CreateEmployeeAccountResponse createEmployeeAccount(CreateEmployeeAccountCommand command) {
        return createEmployeeAccountCommandHandler.handler(command);
    }

    @Override
    public EmployeeSignInResponse signIn(EmployeeSignInCommand command) {
        return employeeSignInCommandHandler.signIn(command);
    }

    @Override
    public void changePassword(EmployeeChangePasswordCommand command) {
        employeeChangePasswordCommandHandler.changePassword(command);
    }

    @Override
    public void sendResetPasswordMail(EmployeeSendResetPasswordMailCommand command) {
        employeeSendResetPasswordMailCommandHandler.handler(command);
    }

    @Override
    public void resetPassword(EmployeeResetPasswordCommand command) {
        employeeResetPasswordCommandHandler.handler(command);
    }

    @Override
    public void deleteEmployeeAccount(DeleteEmployeeAccountCommand command) {
        deleteEmployeeAccountCommandHandler.delete(command);
    }


}
