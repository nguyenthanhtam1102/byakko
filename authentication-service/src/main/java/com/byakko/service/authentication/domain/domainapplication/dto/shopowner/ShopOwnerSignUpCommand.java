package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ShopOwnerSignUpCommand {

    @NotBlank(message = "employee id must be not blank")
    private String username;

    @NotBlank(message = "password must be not blank")
    private String password;

}
