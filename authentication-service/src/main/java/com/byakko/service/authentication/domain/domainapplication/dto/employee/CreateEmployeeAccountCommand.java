package com.byakko.service.authentication.domain.domainapplication.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateEmployeeAccountCommand {

    @NotBlank(message = "employee id must be not blank")
    @JsonProperty("employee_id")
    private String employeeId;

    @NotBlank(message = "password must be not blank")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "role_id must be not null")
    @JsonProperty("role_id")
    private String roleId;

}
