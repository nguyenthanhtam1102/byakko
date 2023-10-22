package com.byakko.service.authentication.domain.domainapplication.handler.employee;

import com.byakko.common.domain.domainapplication.MessageManager;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerChangePasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.employee.EmployeeChangePasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.handler.customer.CustomerCommandHandlerHelper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.EmployeeRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeChangePasswordCommandHandler {

    private final EmployeeCommandHandlerHelper employeeCommandHandlerHelper;
    private final EmployeeRepository employeeRepository;
    private final PasswordUtils passwordUtils;
    private final MessageManager messageManager;

    public EmployeeChangePasswordCommandHandler(EmployeeCommandHandlerHelper employeeCommandHandlerHelper,
                                                EmployeeRepository employeeRepository,
                                                PasswordUtils passwordUtils,
                                                MessageManager messageManager) {
        this.employeeCommandHandlerHelper = employeeCommandHandlerHelper;
        this.employeeRepository = employeeRepository;
        this.passwordUtils = passwordUtils;
        this.messageManager = messageManager;
    }

    public void changePassword(EmployeeChangePasswordCommand command) {
        Employee employee = employeeCommandHandlerHelper.findEmployeeById(command.getId());

        if(!passwordUtils.matches(command.getCurrentPassword(), employee.getPassword()))
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");

        employee.setPassword(passwordUtils.encode(command.getNewPassword()));

        employee.validate();
        employeeRepository.save(employee);
    }

}
