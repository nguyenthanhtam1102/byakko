package com.byakko.service.product.dtos.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategoryCommand {

    @NotBlank(message = "name must be not blank")
    private String name;
    private String description;
    private String parent;

}
