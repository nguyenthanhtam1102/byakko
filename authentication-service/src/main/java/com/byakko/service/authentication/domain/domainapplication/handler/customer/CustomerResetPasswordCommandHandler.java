package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerResetPasswordCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final PasswordUtils passwordUtils;

    public CustomerResetPasswordCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                               CustomerRepository customerRepository,
                                               PasswordUtils passwordUtils) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.passwordUtils = passwordUtils;
    }

    public void handler(CustomerResetPasswordCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerByPhoneOrEmail(command.getPhoneOrEmail(), command.getPhoneOrEmail());

        // Kiểm tra code

        // Nếu code đúng
        customer.setPassword(passwordUtils.encode(command.getNewPassword()));
        customer.validate();

        customerRepository.save(customer);
    }

}
