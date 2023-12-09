package com.byakko.service.sales.service.production.domain.domainapplication.dto.category;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GetProductCategoryCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    @Min(value = 0, message = "depth must be greater than or equal 0")
    @Max(value = 3, message = "depth must be less than or equal 3")
    private int depth = 0;

    public GetProductCategoryCommand(String id, int depth) {
        this.id = id;
        this.depth = depth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
