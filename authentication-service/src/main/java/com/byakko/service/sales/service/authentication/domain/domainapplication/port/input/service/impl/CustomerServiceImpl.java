package com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.sales.service.authentication.domain.domainapplication.handler.customer.VerifyEmailAddressCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.authentication.domain.domainapplication.handler.customer.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.input.service.CustomerService;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.*;
import com.byakko.service.sales.service.authentication.domain.domainapplication.handler.customer.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSignUpCommandHandler customerSignUpCommandHandler;
    private final CustomerSignInCommandHandler customerSignInCommandHandler;
    private final CustomerSignOutCommandHandler customerSignOutCommandHandler;
    private final CustomerChangePasswordCommandHandler customerChangePasswordCommandHandler;
    private final SendResetPasswordMailCommandHandler sendResetPasswordMailCommandHandler;
    private final CustomerResetPasswordCommandHandler customerResetPasswordCommandHandler;
    private final DeleteCustomerCommandHandler deleteCustomerCommandHandler;
    private final ResendEmailAddressVerificationMailCommandHandler resendEmailAddressVerificationMailCommandHandler;
    private final VerifyEmailAddressCommandHandler verifyEmailAddressCommandHandler;

    public CustomerServiceImpl(CustomerSignUpCommandHandler customerSignUpCommandHandler,
                               CustomerSignInCommandHandler customerSignInCommandHandler,
                               CustomerSignOutCommandHandler customerSignOutCommandHandler, CustomerChangePasswordCommandHandler customerChangePasswordCommandHandler,
                               SendResetPasswordMailCommandHandler sendResetPasswordMailCommandHandler,
                               CustomerResetPasswordCommandHandler customerResetPasswordCommandHandler,
                               DeleteCustomerCommandHandler deleteCustomerCommandHandler,
                               ResendEmailAddressVerificationMailCommandHandler resendEmailAddressVerificationMailCommandHandler, VerifyEmailAddressCommandHandler verifyEmailAddressCommandHandler) {
        this.customerSignUpCommandHandler = customerSignUpCommandHandler;
        this.customerSignInCommandHandler = customerSignInCommandHandler;
        this.customerSignOutCommandHandler = customerSignOutCommandHandler;
        this.customerChangePasswordCommandHandler = customerChangePasswordCommandHandler;
        this.sendResetPasswordMailCommandHandler = sendResetPasswordMailCommandHandler;
        this.customerResetPasswordCommandHandler = customerResetPasswordCommandHandler;
        this.deleteCustomerCommandHandler = deleteCustomerCommandHandler;
        this.resendEmailAddressVerificationMailCommandHandler = resendEmailAddressVerificationMailCommandHandler;
        this.verifyEmailAddressCommandHandler = verifyEmailAddressCommandHandler;
    }

    @Override
    public CustomerSignUpResponse signUp(CustomerSignUpCommand command) {
        return customerSignUpCommandHandler.signUp(command);
    }

    @Override
    public CustomerSignInResponse signIn(CustomerSignInCommand command) {
        return customerSignInCommandHandler.signIn(command);
    }

    @Override
    public void signOut(CustomerSignOutCommand command) {
        customerSignOutCommandHandler.handler(command);
    }

    @Override
    public void changePassword(CustomerChangePasswordCommand command) {
        customerChangePasswordCommandHandler.changePassword(command);
    }

    @Override
    public void sendResetPasswordMail(SendResetPasswordMailCommand command) {
        sendResetPasswordMailCommandHandler.handler(command);
    }

    @Override
    public void resetPassword(CustomerResetPasswordCommand command) {
        customerResetPasswordCommandHandler.handler(command);
    }

    @Override
    public void deleteCustomer(DeleteCustomerCommand command) {
        deleteCustomerCommandHandler.delete(command);
    }

    @Override
    public void resendEmailAddressVerificationMail(ResendEmailAddressVerificationMailCommand command) {
        resendEmailAddressVerificationMailCommandHandler.resend(command);
    }

    @Override
    public void verifyEmailAddress(VerifyEmailAddressCommand command) {
        verifyEmailAddressCommandHandler.verify(command);
    }

}
