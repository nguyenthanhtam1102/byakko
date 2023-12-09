package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CustomerSignUpCommand {

    @NotBlank(message = "firstname must be not blank")
    @JsonProperty("firstname")
    private String firstName;

    @NotBlank(message = "lastname must be not blank")
    @JsonProperty("lastname")
    private String lastName;

    @NotBlank(message = "phone must be not blank")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "email must be not blank")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password must be not blank")
    @JsonProperty("password")
    private String password;

}
