package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.application.security.JwtProvider;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSignInCommandHandler {

    private final EmployeeCommandHandlerHelper employeeCommandHandlerHelper;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public EmployeeSignInCommandHandler(EmployeeCommandHandlerHelper employeeCommandHandlerHelper,
                                        EmployeeRepository employeeRepository,
                                        PasswordEncoder passwordEncoder,
                                        JwtProvider jwtProvider) {
        this.employeeCommandHandlerHelper = employeeCommandHandlerHelper;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public EmployeeSignInResponse signIn(EmployeeSignInCommand command) {
        Employee employee = employeeCommandHandlerHelper.findEmployeeById(command.getEmployeeId());

        if(!passwordEncoder.matches(command.getPassword(), employee.getPassword()))
            throw new RuntimeException("Password not correct");

        return EmployeeSignInResponse.Builder.builder()
                .idToken(jwtProvider.generateToken())
                .refreshToken()
                .expiresTime()
                .userId()
                .build();
    }

}
