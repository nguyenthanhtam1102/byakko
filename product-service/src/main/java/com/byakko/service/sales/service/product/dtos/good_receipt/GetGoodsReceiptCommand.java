package com.byakko.service.sales.service.product.dtos.good_receipt;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetGoodsReceiptCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
