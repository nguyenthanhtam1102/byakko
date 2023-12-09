package com.byakko.service.sales.service.production.domain.domainapplication.dto.category;

import com.byakko.service.sales.common.application.dto.ListAllCommand;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ListAllProductCategoryCommand extends ListAllCommand {

    @NotNull(message = "depth must be not null")
    @Min(value = 0, message = "depth must be greater than or equal 0")
    @Max(value = 3, message = "depth must be less than or equal 3")
    private Integer depth = 0;

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
}
