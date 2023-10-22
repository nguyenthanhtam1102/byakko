package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.service.authentication.domain.domainapplication.dto.employee.GetEmployeeAccountCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.GetEmployeeAccountResponse;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class GetEmployeeAccountCommandHandler {

    private final EmployeeCommandHandlerHelper employeeCommandHandlerHelper;

    public GetEmployeeAccountCommandHandler(EmployeeCommandHandlerHelper employeeCommandHandlerHelper) {
        this.employeeCommandHandlerHelper = employeeCommandHandlerHelper;
    }

    public GetEmployeeAccountResponse handler(GetEmployeeAccountCommand command) {
        Employee employee = employeeCommandHandlerHelper.findEmployeeById(command.getUserId());

        return GetEmployeeAccountResponse.Builder.builder()
                .userId(employee.getId().getValue())
                .employeeId(employee.getEmployeeId())
                .roleId(employee.getRole().getId().getValue())
                .build();
    }

}
