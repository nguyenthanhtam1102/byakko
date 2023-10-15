package com.byakko.service.authentication.domain.domainapplication.dto.employee;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EmployeeSignUpCommand {

    @NotBlank(message = "employee id must be not blank")
    private String employeeId;

    @NotBlank(message = "password must be not blank")
    private String password;

}
