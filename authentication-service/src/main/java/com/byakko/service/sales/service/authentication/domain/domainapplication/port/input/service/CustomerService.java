package com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface CustomerService {

    CustomerSignUpResponse signUp(@Valid CustomerSignUpCommand command);
    CustomerSignInResponse signIn(@Valid CustomerSignInCommand command);
    void signOut(@Valid CustomerSignOutCommand command);
    void changePassword(@Valid CustomerChangePasswordCommand command);
    void sendResetPasswordMail(@Valid SendResetPasswordMailCommand command);
    void resetPassword(@Valid CustomerResetPasswordCommand command);
    void deleteCustomer(@Valid DeleteCustomerCommand command);
    void resendEmailAddressVerificationMail(@Valid ResendEmailAddressVerificationMailCommand command);
    void verifyEmailAddress(@Valid VerifyEmailAddressCommand command);

}
