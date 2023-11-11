package com.byakko.service.product.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class GetRootCategoriesCommand {

    @Min(value = 0, message = "depth must be greater than or equal 0")
    @Max(value = 3, message = "depth must be less than or equal 3")
    private int depth = 0;

    @Min(value = 0, message = "page must be greater than or equal 0")
    private int page = 0;

    @Min(value = 0, message = "limit must be greater than or equal 0")
    @Max(value = 100, message = "limit must less than or equal 100")
    private int limit = 15;

}
