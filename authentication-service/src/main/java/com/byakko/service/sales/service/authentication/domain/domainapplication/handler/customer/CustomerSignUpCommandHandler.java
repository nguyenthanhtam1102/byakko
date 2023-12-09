package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.domain.exception.DomainException;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import com.byakko.service.sales.service.authentication.domain.domainapplication.mapper.CustomerMapper;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.MailTemplate;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class CustomerSignUpCommandHandler {

    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;
    private final MailSenderHelper mailSenderHelper;
    private final MailTemplate mailTemplate;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerSignUpCommandHandler(CustomerRepository customerRepository,
                                        TokenRepository tokenRepository,
                                        MailSenderHelper mailSenderHelper,
                                        MailTemplate mailTemplate, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.tokenRepository = tokenRepository;
        this.mailSenderHelper = mailSenderHelper;
        this.mailTemplate = mailTemplate;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerSignUpResponse signUp(CustomerSignUpCommand command) {
        if(customerRepository.findByPhoneAndEmail(command.getPhone(), command.getEmail()).isPresent())
            throw new DomainException("Phone or email is exists");

        Customer customer = CustomerMapper.toCustomer(command);
        customer.validatePassword(customer.getPassword());
        customer.setPassword(passwordEncoder.encode(command.getPassword()));
        customer.initialize();
        customer.validate();
        customerRepository.save(customer);

        Token token = Token.Builder.builder()
                    .userId(customer.getId().getValue())
                     .randomData(UUID.randomUUID().toString())
                    .createdAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)))
                    .expiredTime(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusSeconds(300))
                    .build();

        String rawToken;
        try {
             rawToken = objectMapper.writeValueAsString(token);
        } catch (Exception ex) {
            throw new RuntimeException("Không thể chuyển token sang rawToken");
        }
        token.setHashedData(passwordEncoder.encode(rawToken));

        tokenRepository.save(token);

        mailSenderHelper.send(mailTemplate.getEmailAddressVerificationMailTemplate(
                customer.getEmail(),
                customer.getFirstName() + " " + customer.getLastName(), token.getHashedData())
        );

        return CustomerMapper.toCustomerSignUpResponse(customer);
    }

}
