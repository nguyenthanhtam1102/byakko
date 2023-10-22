package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomerResetPasswordCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordUtils passwordUtils;

    public CustomerResetPasswordCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                               CustomerRepository customerRepository,
                                               TokenRepository tokenRepository,
                                               PasswordUtils passwordUtils) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.tokenRepository = tokenRepository;
        this.passwordUtils = passwordUtils;
    }

    @Transactional
    public void handler(CustomerResetPasswordCommand command) {
        Token token = tokenRepository.findByHashedData(command.getToken())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        token.validate();

        Customer customer = customerCommandHandlerHelper.findCustomerById(token.getUserId());

        token.setUsed(Boolean.TRUE);
        tokenRepository.save(token);

        customer.setPassword(passwordUtils.encode(command.getNewPassword()));
        customer.validate();

        customerRepository.save(customer);
    }

}
