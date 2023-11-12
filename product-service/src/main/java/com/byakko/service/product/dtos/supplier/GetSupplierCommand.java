package com.byakko.service.product.dtos.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetSupplierCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
