package com.byakko.service.authentication.domain.domainapplication;

import com.byakko.common.domain.exception.DomainException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.CustomerMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.AuthenticationDomainService;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.event.CustomerSignUpCompletedEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomerSignUpCommandHandler {

    private final CustomerRepository customerRepository;
    private final AuthenticationDomainService authenticationDomainService;

    public CustomerSignUpCommandHandler(CustomerRepository customerRepository, AuthenticationDomainService authenticationDomainService) {
        this.customerRepository = customerRepository;
        this.authenticationDomainService = authenticationDomainService;
    }

    public CustomerSignUpResponse signUp(CustomerSignUpCommand command) {
        if(customerRepository.findByPhoneAndEmail(command.getPhone(), command.getEmail()).isPresent())
            throw new DomainException("Customer is exists");

        Customer customer = CustomerMapper.toCustomer(command);
        CustomerSignUpCompletedEvent event = authenticationDomainService.validateAndInitializeCustomer(customer);

        customerRepository.save(customer);
        return CustomerMapper.toCustomerSignUpResponse(customer);
    }

}
