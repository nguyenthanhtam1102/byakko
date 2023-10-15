package com.byakko.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.CustomerSignInCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.CustomerSignUpCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSignUpCommandHandler customerSignUpCommandHandler;
    private final CustomerSignInCommandHandler customerSignInCommandHandler;

    public CustomerServiceImpl(CustomerSignUpCommandHandler customerSignUpCommandHandler, CustomerSignInCommandHandler customerSignInCommandHandler) {
        this.customerSignUpCommandHandler = customerSignUpCommandHandler;
        this.customerSignInCommandHandler = customerSignInCommandHandler;
    }

    @Override
    public CustomerSignUpResponse signUp(CustomerSignUpCommand command) {
        return customerSignUpCommandHandler.signUp(command);
    }

    @Override
    public CustomerSignInResponse signIn(CustomerSignInCommand command) {
        return customerSignInCommandHandler.signIn(command);
    }

}
