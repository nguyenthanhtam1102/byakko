package com.byakko.service.production.domain.domainapplication.dto.asset;

import javax.validation.constraints.NotBlank;

public class DeleteAssetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public DeleteAssetCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
