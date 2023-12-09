package com.byakko.service.sales.service.product.dtos.purchase_order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class DeletePurchaseOrderCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
