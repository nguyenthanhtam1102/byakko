package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee.DeleteEmployeeAccountCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.EmployeeStatus;
import org.springframework.stereotype.Component;

@Component
public class DeleteEmployeeAccountCommandHandler {

    private final EmployeeCommandHandlerHelper employeeCommandHandlerHelper;
    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeAccountCommandHandler(
            EmployeeCommandHandlerHelper employeeCommandHandlerHelper,
            EmployeeRepository employeeRepository) {
        this.employeeCommandHandlerHelper = employeeCommandHandlerHelper;
        this.employeeRepository = employeeRepository;
    }

    public void delete(DeleteEmployeeAccountCommand command) {
        Employee employee = employeeCommandHandlerHelper.findEmployeeById(command.getId());

        employee.setStatus(EmployeeStatus.DELETED);
        employee.validate();

        employeeRepository.save(employee);
    }

}
