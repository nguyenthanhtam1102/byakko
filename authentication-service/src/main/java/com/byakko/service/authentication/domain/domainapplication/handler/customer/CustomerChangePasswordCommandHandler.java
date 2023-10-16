package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerChangePasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerChangePasswordCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final PasswordUtils passwordUtils;

    public CustomerChangePasswordCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                                CustomerRepository customerRepository,
                                                PasswordUtils passwordUtils) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.passwordUtils = passwordUtils;
    }

    public void changePassword(CustomerChangePasswordCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerById(command.getId());

        if(!passwordUtils.matches(command.getCurrentPassword(), customer.getPassword()))
            throw new RuntimeException("Password not correct");

        customer.setPassword(passwordUtils.encode(command.getNewPassword()));

        customer.validate();
        customerRepository.save(customer);
    }

}
