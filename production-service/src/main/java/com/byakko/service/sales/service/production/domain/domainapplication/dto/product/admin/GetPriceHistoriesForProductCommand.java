package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin;

import com.byakko.service.sales.common.application.dto.ListAllCommand;

import javax.validation.constraints.NotBlank;

public class GetPriceHistoriesForProductCommand extends ListAllCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public GetPriceHistoriesForProductCommand(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
