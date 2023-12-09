package com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.ResendEmailAddressVerificationMailCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.SendResetPasswordMailCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.VerifyEmailAddressCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.*;
import com.byakko.service.authentication.domain.domainapplication.handler.shopowner.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.shopowner.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.ShopOwnerService;
import com.byakko.service.sales.service.authentication.domain.domainapplication.handler.shopowner.*;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerServiceImpl implements ShopOwnerService {

    private final ShopOwnerSignInCommandHandler shopOwnerSignInCommandHandler;
    private final ShopOwnerSignUpCommandHandler shopOwnerSignUpCommandHandler;
    private final ShopOwnerChangePasswordCommandHandler shopOwnerChangePasswordCommandHandler;
    private final ShopOwnerResendEmailAddressVerificationMailCommandHandler shopOwnerResendEmailAddressVerificationMailCommandHandler;
    private final ShopOwnerResetPasswordCommandHandler shopOwnerResetPasswordCommandHandler;
    private final ShopOwnerVerifyEmailAddressCommandHandler shopOwnerVerifyEmailAddressCommandHandler;
    private final ShopOwnerSendResetPasswordMainCommandHandler shopOwnerSendResetPasswordMainCommandHandler;
    private final ShopOwnerDeleteCommandHandler shopOwnerDeleteCommandHandler;

    public ShopOwnerServiceImpl(ShopOwnerSignInCommandHandler shopOwnerSignInCommandHandler,
                                ShopOwnerSignUpCommandHandler shopOwnerSignUpCommandHandler, ShopOwnerChangePasswordCommandHandler shopOwnerChangePasswordCommandHandler, ShopOwnerResendEmailAddressVerificationMailCommandHandler shopOwnerResendEmailAddressVerificationMailCommandHandler, ShopOwnerResetPasswordCommandHandler shopOwnerResetPasswordCommandHandler, ShopOwnerVerifyEmailAddressCommandHandler shopOwnerVerifyEmailAddressCommandHandler, ShopOwnerSendResetPasswordMainCommandHandler shopOwnerSendResetPasswordMainCommandHandler, ShopOwnerDeleteCommandHandler shopOwnerDeleteCommandHandler) {
        this.shopOwnerSignInCommandHandler = shopOwnerSignInCommandHandler;
        this.shopOwnerSignUpCommandHandler = shopOwnerSignUpCommandHandler;
        this.shopOwnerChangePasswordCommandHandler = shopOwnerChangePasswordCommandHandler;
        this.shopOwnerResendEmailAddressVerificationMailCommandHandler = shopOwnerResendEmailAddressVerificationMailCommandHandler;
        this.shopOwnerResetPasswordCommandHandler = shopOwnerResetPasswordCommandHandler;
        this.shopOwnerVerifyEmailAddressCommandHandler = shopOwnerVerifyEmailAddressCommandHandler;
        this.shopOwnerSendResetPasswordMainCommandHandler = shopOwnerSendResetPasswordMainCommandHandler;
        this.shopOwnerDeleteCommandHandler = shopOwnerDeleteCommandHandler;
    }

    @Override
    public ShopOwnerSignInResponse signIn(ShopOwnerSignInCommand command) {
        return shopOwnerSignInCommandHandler.signIn(command);
    }

    @Override
    public ShopOwnerSignUpResponse signUp(ShopOwnerSignUpCommand command) {
        return shopOwnerSignUpCommandHandler.signUp(command);
    }

    @Override
    public void changePassword(ShopOwnerChangePasswordCommand command) {
        shopOwnerChangePasswordCommandHandler.changePassword(command);
    }

    @Override
    public void sendResetPasswordMail(SendResetPasswordMailCommand command) {
        shopOwnerSendResetPasswordMainCommandHandler.handler(command);
    }

    @Override
    public void resetPassword(ShopOwnerResetPasswordCommand command) {
        shopOwnerResetPasswordCommandHandler.handler(command);
    }

    @Override
    public void deleteShopOwner(DeleteShopOwner command) {
        shopOwnerDeleteCommandHandler.delete(command);
    }

    @Override
    public void resendEmailAddressVerificationMail(ResendEmailAddressVerificationMailCommand command) {
        shopOwnerResendEmailAddressVerificationMailCommandHandler.resend(command);
    }

    @Override
    public void verifyEmailAddress(VerifyEmailAddressCommand command) {
        shopOwnerVerifyEmailAddressCommandHandler.verify(command);
    }
}
