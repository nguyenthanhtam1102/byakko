package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CustomerSignInCommand {

    @NotBlank(message = "phone or email ust be not blank")
    private String phoneOrEmail;

    @NotBlank(message = "password must be not blank")
    private String password;

}
