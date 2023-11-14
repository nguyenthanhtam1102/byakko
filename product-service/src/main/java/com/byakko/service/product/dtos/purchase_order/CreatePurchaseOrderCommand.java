package com.byakko.service.product.dtos.purchase_order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreatePurchaseOrderCommand {

    @NotBlank(message = "employee must be not blank")
    private String employee;

    @NotBlank(message = "supplier must be not blank")
    private String supplier;

    @DecimalMin(value = "0", inclusive = true, message = "discount must be greater than or equal 0")
    private BigDecimal discount;

    @DecimalMin(value = "0", inclusive = true, message = "surcharge must be greater than or equal 0")
    private BigDecimal surcharge;

    @DecimalMin(value = "0", inclusive = true, message = "tax must be greater than or equal 0")
    private BigDecimal tax;

    @DecimalMin(value = "0", inclusive = true, message = "delivery_charge must be greater than or equal 0")
    @JsonProperty("delivery_charge")
    private BigDecimal deliveryCharge;

    private String note;

    @NotNull(message = "purchase_order_details must be not null")
    @NotEmpty(message = "purchase_order_details must be not empty")
    @JsonProperty("purchase_order_details")
    private Set<CreatePurchaseOrderDetailCommand> purchaseOrderDetails;

}
