package com.byakko.service.sales.service.product.dtos.assets;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetAssetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
