package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CustomerSignUpCommand {

    @NotBlank(message = "firstname must be not blank")
    private String firstName;

    @NotBlank(message = "lastname must be not blank")
    private String lastName;

    @NotBlank(message = "phone must be not blank")
    private String phone;

    @NotBlank(message = "email must be not blank")
    private String email;

    @NotBlank(message = "password must be not blank")
    private String password;

}
