package com.byakko.service.authentication.domain.domainapplication.dto;

import javax.validation.constraints.NotBlank;

public class SignInCommand {

    @NotBlank(message = "username must be not blank")
    private String username;

    @NotBlank(message = "password must be not blank")
    private String password;

}
