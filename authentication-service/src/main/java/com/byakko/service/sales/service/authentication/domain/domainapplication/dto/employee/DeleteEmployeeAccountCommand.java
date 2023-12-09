package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee;

import javax.validation.constraints.NotBlank;

public class DeleteEmployeeAccountCommand {

    @NotBlank(message = "id must be not blank")
    private final String id;

    public DeleteEmployeeAccountCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
