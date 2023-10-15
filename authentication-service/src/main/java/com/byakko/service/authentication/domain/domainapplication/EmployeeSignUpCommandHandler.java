package com.byakko.service.authentication.domain.domainapplication;

import com.byakko.common.domain.exception.DomainException;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.EmployeeMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domaincore.AuthenticationDomainService;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSignUpCommandHandler {

    private final AuthenticationDomainService authenticationDomainService;
    private final EmployeeRepository employeeRepository;

    public EmployeeSignUpCommandHandler(AuthenticationDomainService authenticationDomainService,
                                        EmployeeRepository employeeRepository) {
        this.authenticationDomainService = authenticationDomainService;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeSignUpResponse signUp(EmployeeSignUpCommand command) {
        if(employeeRepository.findByEmployeeId(command.getEmployeeId()).isPresent())
            throw new DomainException("Employee is exists");

        Employee employee = EmployeeMapper.toEmployee(command);
        authenticationDomainService.validateAndInitializeEmployee(employee);
        employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeSignUpResponse(employee);
    }

}
