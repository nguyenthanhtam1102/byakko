package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerDetailsResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerGetDetailsHandler {
    private final CustomerRepository customerRepository;
    public CustomerGetDetailsHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerDetailsResponse getCustomer(String id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Customer not found"));
        return CustomerDetailsResponse.Builder.builder()
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
