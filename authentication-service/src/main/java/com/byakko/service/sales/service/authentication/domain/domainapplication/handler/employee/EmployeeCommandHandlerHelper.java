package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandHandlerHelper {

    private final EmployeeRepository employeeRepository;

    public EmployeeCommandHandlerHelper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeById(String id) {
        return employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee %s not found", id)));
    }

}
