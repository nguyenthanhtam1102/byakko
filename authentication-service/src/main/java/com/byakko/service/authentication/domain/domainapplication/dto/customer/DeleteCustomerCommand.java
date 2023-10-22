package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class DeleteCustomerCommand {

    @NotBlank(message = "id must be not blank")
    private final String id;

    public DeleteCustomerCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
