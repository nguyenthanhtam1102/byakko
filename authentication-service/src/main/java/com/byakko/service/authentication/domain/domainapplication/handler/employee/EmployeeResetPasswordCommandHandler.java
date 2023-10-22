package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeResetPasswordCommandHandler {

    private final EmployeeCommandHandlerHelper employeeCommandHandlerHelper;
    private final EmployeeRepository employeeRepository;
    private final TokenRepository tokenRepository;
    private final PasswordUtils passwordUtils;

    public EmployeeResetPasswordCommandHandler(EmployeeCommandHandlerHelper employeeCommandHandlerHelper,
                                               EmployeeRepository employeeRepository,
                                               TokenRepository tokenRepository,
                                               PasswordUtils passwordUtils) {
        this.employeeCommandHandlerHelper = employeeCommandHandlerHelper;
        this.employeeRepository = employeeRepository;
        this.tokenRepository = tokenRepository;
        this.passwordUtils = passwordUtils;
    }

    @Transactional
    public void handler(EmployeeResetPasswordCommand command) {
        Token token = tokenRepository.findByHashedData(command.getToken())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        token.validate();

        Employee employee = employeeCommandHandlerHelper.findEmployeeById(token.getUserId());

        token.setUsed(Boolean.TRUE);
        tokenRepository.save(token);

        employee.setPassword(passwordUtils.encode(command.getNewPassword()));
        employee.validate();

        employeeRepository.save(employee);
    }

}
