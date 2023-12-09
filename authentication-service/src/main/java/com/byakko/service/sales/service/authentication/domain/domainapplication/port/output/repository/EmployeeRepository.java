package com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface EmployeeRepository {

    Optional<Employee> findByEmployeeId(String id);
    Employee save(Employee employee);

}
