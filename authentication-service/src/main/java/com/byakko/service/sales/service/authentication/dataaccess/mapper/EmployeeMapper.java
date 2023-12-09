package com.byakko.service.sales.service.authentication.dataaccess.mapper;

import com.byakko.service.sales.service.authentication.dataaccess.entity.EmployeeEntity;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.UserId;

public class EmployeeMapper {

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId().getValue())
                .password(employee.getPassword())
                .role(employee.getRole() != null ? RoleMapper.toRoleEntity(employee.getRole()) : null)
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public static Employee toEmployee(EmployeeEntity employee) {
        return Employee.Builder.builder()
                .id(new UserId(employee.getId()))
                .password(employee.getPassword())
                .role(employee.getRole() != null ? RoleMapper.toRole(employee.getRole()) : null)
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

}
