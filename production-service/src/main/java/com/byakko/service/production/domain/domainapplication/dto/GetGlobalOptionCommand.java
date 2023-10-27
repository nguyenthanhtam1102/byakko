package com.byakko.service.production.domain.domainapplication.dto;

public class GetGlobalOptionCommand {

    private String id;

    public GetGlobalOptionCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
