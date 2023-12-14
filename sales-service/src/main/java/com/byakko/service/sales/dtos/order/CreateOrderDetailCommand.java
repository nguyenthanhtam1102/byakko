package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateOrderDetailCommand {

    @NotBlank(message = "product must be not blank")
    private String product;

    private String variant;

    @NotNull(message = "unit_price must be not null")
    @DecimalMin(value = "0", inclusive = true, message = "unit_price must be greater than or equal 0")
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @NotNull(message = "quantity must be not null")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

}
