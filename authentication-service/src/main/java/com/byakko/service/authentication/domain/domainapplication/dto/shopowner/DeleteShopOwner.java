package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

import javax.validation.constraints.NotBlank;

public class DeleteShopOwner {

    @NotBlank(message = "id must be not blank")
    private final String id;

    public DeleteShopOwner(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

