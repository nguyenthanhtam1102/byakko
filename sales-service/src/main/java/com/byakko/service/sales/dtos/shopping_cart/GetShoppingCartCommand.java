package com.byakko.service.sales.dtos.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetShoppingCartCommand {

    @NotBlank(message = "customer_id must be not blank")
    private String customerId;

}
