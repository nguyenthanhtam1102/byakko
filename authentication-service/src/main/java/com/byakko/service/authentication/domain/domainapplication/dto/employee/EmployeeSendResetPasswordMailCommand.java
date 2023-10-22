package com.byakko.service.authentication.domain.domainapplication.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EmployeeSendResetPasswordMailCommand {

    @NotBlank(message = "employee_id must be not blank")
    @JsonProperty("employee_id")
    private final String employeeId;

    public EmployeeSendResetPasswordMailCommand(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
