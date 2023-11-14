package com.byakko.service.product.dtos.good_receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateGoodReceiptDetailCommand {

    @NotBlank(message = "purchase_order_detail must be not blank")
    @JsonProperty("purchase_order_detail")
    @EqualsAndHashCode.Include
    private String purchaseOrderDetail;

    @NotNull(message = "received_qty must be not null")
    @Min(value = 0, message = "received_qty must be greater than or equal 0")
    @JsonProperty("received_qty")
    private Integer receivedQuantity;

    @NotNull(message = "rejected_qty must be not null")
    @Min(value = 0, message = "rejected_qty must be greater than or equal 0")
    @JsonProperty("rejected_qty")
    private Integer rejectedQuantity;

    private String note;

}
