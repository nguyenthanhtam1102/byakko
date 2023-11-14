package com.byakko.service.product.dtos.purchase_order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreatePurchaseOrderDetailCommand {

    @NotBlank(message = "product must be not blank")
    @EqualsAndHashCode.Include
    private String product;

    @JsonProperty("product_variant")
    @EqualsAndHashCode.Include
    private String productVariant;

    @NotNull(message = "unit_price must be not null")
    @DecimalMin(value = "0", inclusive = true, message = "unit_price must be greater than or equal 0")
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @NotNull(message = "order_qty must be not null")
    @Min(value = 0, message = "order_qty must be greater than or equal 0")
    @JsonProperty("order_qty")
    private Integer orderQuantity;

}
