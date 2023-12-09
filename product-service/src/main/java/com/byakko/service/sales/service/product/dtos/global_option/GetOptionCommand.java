package com.byakko.service.sales.service.product.dtos.global_option;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetOptionCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
