package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class VerifyEmailAddressCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    @NotBlank(message = "code must be not blank")
    private String code;

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
