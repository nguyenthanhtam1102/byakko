package com.byakko.service.product.dtos.purchase_order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class UpdatePurchaseOrderCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private BigDecimal surcharge;
    private BigDecimal tax;

    @JsonProperty("delivery_charge")
    private BigDecimal deliveryCharge;

    private String note;

    @JsonProperty("purchase_order_details")
    private Set<CreatePurchaseOrderDetailCommand> purchaseOrderDetails;

}
