package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerCommandHandlerHelper {

    private final CustomerRepository customerRepository;

    public CustomerCommandHandlerHelper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Customer %s not found", id)));
    }

    public Customer findCustomerByPhoneOrEmail(String phone, String email) {
        return customerRepository.findByPhoneOrEmail(phone, email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

}
