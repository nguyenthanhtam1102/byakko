package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ShopOwnerSignUpCommand {
    @NotBlank(message = "phone must be not blank")
    @JsonProperty("username")
    private String username;
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
