package com.byakko.service.product.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetCategoryCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    @Min(value = 0, message = "depth must be greater than or equal 0")
    @Max(value = 3, message = "depth must be less than or equal 3")
    private int depth = 0;

}
