package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class CustomerSignOutCommand {

    @NotBlank(message = "userId must be not blank")
    private String userId;

    public CustomerSignOutCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
