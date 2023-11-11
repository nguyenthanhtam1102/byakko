package com.byakko.service.product.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class DeleteCategoryCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    @Min(value = 0, message = "delete_option: [0, 1, 2]")
    @Max(value = 2, message = "delete_option: [0, 1, 2]")
    private int deleteOption;

}
