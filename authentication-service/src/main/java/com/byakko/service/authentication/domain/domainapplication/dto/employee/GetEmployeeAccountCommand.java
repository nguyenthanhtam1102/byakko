package com.byakko.service.authentication.domain.domainapplication.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class GetEmployeeAccountCommand {

    @NotBlank(message = "user_id must be not blank")
    @JsonProperty("user_id")
    private final String userId;

    public GetEmployeeAccountCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
