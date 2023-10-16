package com.byakko.service.authentication.domain;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.VerifyEmailAddressCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class VerifyEmailAddressCommandHandler {

    private final CustomerRepository customerRepository;

    public VerifyEmailAddressCommandHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void verify(VerifyEmailAddressCommand command) {
        Customer customer = customerRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        // So sánh code

        // Nếu code đúng
        customer.setVerified(Boolean.TRUE);
        customer.validate();

        customerRepository.save(customer);
    }

}
