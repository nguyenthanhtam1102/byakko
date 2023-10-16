package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.common.domain.exception.DomainException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.CustomerMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import com.byakko.service.authentication.domain.domainapplication.utils.MailTemplate;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerSignUpCommandHandler {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderHelper mailSenderHelper;

    public CustomerSignUpCommandHandler(CustomerRepository customerRepository,
                                        PasswordEncoder passwordEncoder, MailSenderHelper mailSenderHelper) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderHelper = mailSenderHelper;
    }

    public CustomerSignUpResponse signUp(CustomerSignUpCommand command) {
        if(customerRepository.findByPhoneAndEmail(command.getPhone(), command.getEmail()).isPresent())
            throw new DomainException("Customer is exists");

        Customer customer = CustomerMapper.toCustomer(command);
        customer.setPassword(passwordEncoder.encode(command.getPassword()));
        customer.initialize();
        customer.validate();
        customerRepository.save(customer);

        mailSenderHelper.send(MailTemplate.getEmailAddressVerificationMailTemplate(customer.getEmail()));

        return CustomerMapper.toCustomerSignUpResponse(customer);
    }

}
