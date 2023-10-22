package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.VerifyEmailAddressCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class VerifyEmailAddressCommandHandler {

    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;

    public VerifyEmailAddressCommandHandler(CustomerRepository customerRepository, TokenRepository tokenRepository) {
        this.customerRepository = customerRepository;
        this.tokenRepository = tokenRepository;
    }


    @Transactional
    public void verify(VerifyEmailAddressCommand command) {
        Token token = tokenRepository.findByHashedData(command.getToken())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        token.validate();

        Customer customer = customerRepository.findById(token.getUserId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        token.setUsed(Boolean.TRUE);
        tokenRepository.save(token);

        customer.setVerified(true);
        customer.validate();

        customerRepository.save(customer);
    }

}
