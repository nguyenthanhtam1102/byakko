package com.byakko.service.production.domain.domainapplication.dto.asset;

import javax.validation.constraints.NotBlank;

public class GetAssetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    public GetAssetCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
