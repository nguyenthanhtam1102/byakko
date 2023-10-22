package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.common.DomainConstants;
import com.byakko.service.authentication.application.security.JwtProvider;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CustomerSignInCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Value("${jwt.expiration-time}")
    private long TOKEN_EXPIRATION_TIME;

    public CustomerSignInCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                        CustomerRepository customerRepository,
                                        PasswordEncoder passwordEncoder,
                                        JwtProvider jwtProvider) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public CustomerSignInResponse signIn(CustomerSignInCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerByPhoneOrEmail(command.getPhoneOrEmail(), command.getPhoneOrEmail());

        if(!passwordEncoder.matches(command.getPassword(), customer.getPassword()))
            throw new AuthenticationException("Password not correct");

        return CustomerSignInResponse.Builder.builder()
                .idToken(jwtProvider.generateToken(customer))
                .refreshToken("")
                .expiresTime(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusSeconds(TOKEN_EXPIRATION_TIME).toEpochSecond())
                .userId(customer.getId().getValue())
                .build();
    }

}
