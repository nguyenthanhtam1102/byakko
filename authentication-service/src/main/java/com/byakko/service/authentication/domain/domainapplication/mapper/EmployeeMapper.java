package com.byakko.service.authentication.domain.domainapplication.mapper;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeSignUpResponse;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;

public class EmployeeMapper {

    public static Employee toEmployee(EmployeeSignUpCommand command) {
        return Employee.Builder.builder()
                .employeeId(command.getEmployeeId())
                .password(command.getPassword())
                .build();
    }

    public static EmployeeSignUpResponse toEmployeeSignUpResponse(Employee employee) {
        return EmployeeSignUpResponse.Builder.builder()
                .userId(employee.getId().getValue().toString())
                .employeeId(employee.getEmployeeId())
                .createdAt(employee.getCreatedAt().toEpochSecond())
                .build();
    }

}
