package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.DeleteCustomerCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.valueobject.CustomerStatus;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;

    public DeleteCustomerCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                        CustomerRepository customerRepository) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
    }

    public void delete(DeleteCustomerCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerById(command.getId());

        customer.setStatus(CustomerStatus.DELETED);
        customer.validate();

        customerRepository.save(customer);
    }

}
