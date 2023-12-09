package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class SendResetPasswordMailCommand {

    @NotBlank(message = "phone or email must be not blank")
    private final String phoneOrEmail;

    public SendResetPasswordMailCommand(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }
}
