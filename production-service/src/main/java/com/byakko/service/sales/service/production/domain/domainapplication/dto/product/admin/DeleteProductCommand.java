package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin;

import javax.validation.constraints.NotBlank;

public class DeleteProductCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public DeleteProductCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
