package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class VerifyEmailAddressCommand {

    @NotBlank(message = "code must be not blank")
    private final String token;

    public VerifyEmailAddressCommand(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
