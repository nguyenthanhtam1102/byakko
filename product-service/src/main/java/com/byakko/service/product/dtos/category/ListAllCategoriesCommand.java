package com.byakko.service.product.dtos.category;

import com.byakko.common.application.dto.ListAllCommand;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ListAllCategoriesCommand extends ListAllCommand {

    @Min(value = 0, message = "depth must be greater than or equal 0")
    @Max(value = 3, message = "depth must be less than or equal 3")
    int depth = 0;

}
