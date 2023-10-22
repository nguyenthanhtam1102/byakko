package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.common.domain.exception.DomainException;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.CreateEmployeeAccountCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.CreateEmployeeAccountResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.EmployeeMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeeAccountCommandHandler {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateEmployeeAccountCommandHandler(EmployeeRepository employeeRepository,
                                               RoleRepository roleRepository,
                                               PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateEmployeeAccountResponse handler(CreateEmployeeAccountCommand command) {
        if(employeeRepository.findByEmployeeId(command.getEmployeeId()).isPresent())
            throw new DomainException("Employee account is exists");

        Role role = roleRepository.findById(command.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        Employee employee = EmployeeMapper.toEmployee(command);
        employee.validatePassword(employee.getPassword());
        employee.setPassword(passwordEncoder.encode(command.getPassword()));
        employee.setRole(role);
        employee.initialize();
        employee.validate();
        employeeRepository.save(employee);

        return EmployeeMapper.toCreateEmployeeAccountResponse(employee);
    }

}
