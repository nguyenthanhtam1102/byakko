package com.byakko.service.product.dtos.global_option;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UpdateOptionCommand {

    @NotBlank(message = "id must be not blank")
    private String id;
    private String name;
    private Set<String> values;

}
