package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface ShopOwnerService {

    ShopOwnerSignInResponse signIn(@Validated ShopOwnerSignInCommand command);
    ShopOwnerSignUpResponse signUp(@Validated ShopOwnerSignUpCommand command);
    void changePassword(@Valid ShopOwnerChangePasswordCommand command);
    void sendResetPasswordMail(@Valid SendResetPasswordMailCommand command);
    void resetPassword(@Valid ShopOwnerResetPasswordCommand command);
    void deleteCustomer(@Valid DeleteShopOwner command);
    void resendEmailAddressVerificationMail(@Valid ResendEmailAddressVerificationMailCommand command);
    void verifyEmailAddress(@Valid VerifyEmailAddressCommand command);
}
