package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.common.domain.exception.DomainException;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.EmployeeMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSignUpCommandHandler {

    private final EmployeeRepository employeeRepository;

    public EmployeeSignUpCommandHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeSignUpResponse signUp(EmployeeSignUpCommand command) {
        if(employeeRepository.findByEmployeeId(command.getEmployeeId()).isPresent())
            throw new DomainException("Employee is exists");

        Employee employee = EmployeeMapper.toEmployee(command);
        employee.initialize();
        employee.validate();
        employeeRepository.save(employee);

        return EmployeeMapper.toEmployeeSignUpResponse(employee);
    }

}
