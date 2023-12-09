package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CustomerSignInCommand {

    @NotBlank(message = "phone or email ust be not blank")
    @JsonProperty("phone_or_email")
    private String phoneOrEmail;

    @NotBlank(message = "password must be not blank")
    @JsonProperty("password")
    private String password;

}
