package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ResendEmailAddressVerificationMailCommand {

    @NotBlank(message = "id must be not blank")
    @JsonProperty("id")
    private final String id;

    public ResendEmailAddressVerificationMailCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
