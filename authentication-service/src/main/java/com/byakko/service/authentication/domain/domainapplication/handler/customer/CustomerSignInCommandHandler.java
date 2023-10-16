package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.application.security.JwtProvider;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerSignInCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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
            throw new RuntimeException("Password not correct");

        return CustomerSignInResponse.Builder.builder()
                .idToken(jwtProvider.generateToken())
                .refreshToken("")
                .expiresTime()
                .userId(customer.getId())
                .build();
    }

}
