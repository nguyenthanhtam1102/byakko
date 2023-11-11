package com.byakko.service.product.dtos.global_option;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CreateOptionCommand {

    @NotBlank(message = "name must be not blank")
    private String name;
    private Set<String> values;

}
