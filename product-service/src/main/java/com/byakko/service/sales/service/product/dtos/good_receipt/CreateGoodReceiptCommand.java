package com.byakko.service.sales.service.product.dtos.good_receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CreateGoodReceiptCommand {

    @NotBlank(message = "purchase_order must be not blank")
    @JsonProperty("purchase_order")
    private String purchaseOrder;

    @NotBlank(message = "employee must be not blank")
    private String employee;

    private Set<String> asset;

    @NotNull(message = "good_receipt_details must be not null")
    @NotEmpty(message = "good_receipt_details must be not empty")
    @JsonProperty("good_receipt_details")
    private Set<CreateGoodReceiptDetailCommand> goodReceiptDetails;

    private String note;

}
