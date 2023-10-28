package com.byakko.service.production.domain.domainapplication.dto.global_option;

public class DeleteGlobalOptionCommand {

    private String id;

    public DeleteGlobalOptionCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
