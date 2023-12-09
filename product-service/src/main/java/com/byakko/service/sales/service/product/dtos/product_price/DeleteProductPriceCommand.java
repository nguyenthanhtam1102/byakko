package com.byakko.service.sales.service.product.dtos.product_price;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class DeleteProductPriceCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
