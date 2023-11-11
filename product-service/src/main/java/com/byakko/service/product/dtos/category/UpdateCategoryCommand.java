package com.byakko.service.product.dtos.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateCategoryCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String name;

    private String description;

    private String parent;

}
