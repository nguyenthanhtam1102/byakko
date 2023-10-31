package com.byakko.service.production.domain.domainapplication.dto.category;

import javax.validation.constraints.NotBlank;

public class DeleteProductCategoryCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private int handleChild;

    public DeleteProductCategoryCommand(String id, int handleChild) {
        this.id = id;
        this.handleChild = handleChild;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHandleChild() {
        return handleChild;
    }

    public void setHandleChild(int handleChild) {
        this.handleChild = handleChild;
    }
}
