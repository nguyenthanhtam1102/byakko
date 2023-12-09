package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.sales.common.domain.domainapplication.MessageManager;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.CustomerChangePasswordCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerChangePasswordCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final PasswordUtils passwordUtils;
    private final MessageManager messageManager;

    public CustomerChangePasswordCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                                CustomerRepository customerRepository,
                                                PasswordUtils passwordUtils,
                                                MessageManager messageManager) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.passwordUtils = passwordUtils;
        this.messageManager = messageManager;
    }

    public void changePassword(CustomerChangePasswordCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerById(command.getId());

        if(!passwordUtils.matches(command.getCurrentPassword(), customer.getPassword()))
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");

        customer.setPassword(passwordUtils.encode(command.getNewPassword()));

        customer.validate();
        customerRepository.save(customer);
    }

}
