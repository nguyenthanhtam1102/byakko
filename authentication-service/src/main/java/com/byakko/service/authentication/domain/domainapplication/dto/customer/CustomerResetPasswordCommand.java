package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class CustomerResetPasswordCommand {

    @NotBlank(message = "phone or email must be not blank")
    private String phoneOrEmail;

    @NotBlank(message = "code must be not blank")
    private String code;

    @NotBlank(message = "newPassword must be not blank")
    private String newPassword;

    public CustomerResetPasswordCommand(String phoneOrEmail, String code, String newPassword) {
        this.phoneOrEmail = phoneOrEmail;
        this.code = code;
        this.newPassword = newPassword;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public String getCode() {
        return code;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
