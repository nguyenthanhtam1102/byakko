package com.byakko.service.authentication.dataaccess.mapper;

import com.byakko.service.authentication.dataaccess.entity.EmployeeEntity;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeId;

public class EmployeeMapper {

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId().getValue())
                .employeeId(employee.getEmployeeId())
                .password(employee.getPassword())
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public static Employee toEmployee(EmployeeEntity employee) {
        return Employee.EmployeeBuilder.builder()
                .id(new EmployeeId(employee.getId()))
                .employeeId(employee.getEmployeeId())
                .password(employee.getPassword())
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

}
