package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface CustomerService {

    CustomerSignUpResponse signUp(@Valid CustomerSignUpCommand command);
    CustomerSignInResponse signIn(@Valid CustomerSignInCommand command);

}
