package com.byakko.service.sales.service.authentication.dataaccess.adapter;

import com.byakko.service.sales.service.authentication.dataaccess.entity.EmployeeEntity;
import com.byakko.service.sales.service.authentication.dataaccess.mapper.EmployeeMapper;
import com.byakko.service.sales.service.authentication.dataaccess.repository.EmployeeJpaRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Optional<Employee> findByEmployeeId(String id) {
        return employeeJpaRepository.findById(id).map(EmployeeMapper::toEmployee);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employee);
        employeeJpaRepository.save(employeeEntity);
        return EmployeeMapper.toEmployee(employeeEntity);
    }
}
