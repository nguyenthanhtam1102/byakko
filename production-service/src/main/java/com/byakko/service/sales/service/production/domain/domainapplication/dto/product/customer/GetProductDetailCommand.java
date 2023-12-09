package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer;

import javax.validation.constraints.NotBlank;

public class GetProductDetailCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public GetProductDetailCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
