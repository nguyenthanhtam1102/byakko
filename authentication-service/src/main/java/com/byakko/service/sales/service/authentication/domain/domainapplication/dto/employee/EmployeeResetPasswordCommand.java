package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EmployeeResetPasswordCommand {

    @NotBlank(message = "token must be not blank")
    @JsonProperty("token")
    private String token;

    @NotBlank(message = "new_password must be not blank")
    @JsonProperty("new_password")
    private String newPassword;

    public EmployeeResetPasswordCommand(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
