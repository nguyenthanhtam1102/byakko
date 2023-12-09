package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin;

import javax.validation.constraints.NotBlank;

public class GetProductCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public GetProductCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
