package com.byakko.service.product.dtos.global_option;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateOptionCommand {

    @NotBlank(message = "name must be not blank")
    @EqualsAndHashCode.Include
    private String name;

    @NotEmpty(message = "values must be not empty")
    private Set<String> values;

}
