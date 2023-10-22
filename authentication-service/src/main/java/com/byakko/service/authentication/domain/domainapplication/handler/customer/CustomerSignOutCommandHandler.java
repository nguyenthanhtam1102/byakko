package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignOutCommand;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerSignOutCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;

    public CustomerSignOutCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
    }

    public void handler(CustomerSignOutCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerById(command.getUserId());
    }

}
