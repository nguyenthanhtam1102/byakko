package com.byakko.service.product.dtos.purchase_order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetPurchaseOrderCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
