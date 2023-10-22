package com.byakko.service.authentication.domain.domainapplication.mapper;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.CreateEmployeeAccountCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.CreateEmployeeAccountResponse;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;

public class EmployeeMapper {

    public static Employee toEmployee(CreateEmployeeAccountCommand command) {
        return Employee.Builder.builder()
                .employeeId(command.getEmployeeId())
                .password(command.getPassword())
                .build();
    }

    public static CreateEmployeeAccountResponse toCreateEmployeeAccountResponse(Employee employee) {
        return CreateEmployeeAccountResponse.Builder.builder()
                .userId(employee.getId().getValue())
                .employeeId(employee.getEmployeeId())
                .createdAt(employee.getCreatedAt().toEpochSecond())
                .build();
    }

}
