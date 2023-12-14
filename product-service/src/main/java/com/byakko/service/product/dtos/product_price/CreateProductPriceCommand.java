package com.byakko.service.product.dtos.product_price;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateProductPriceCommand {

    @NotBlank(message = "product must be not blank")
    private String product;

    private String variant;

    @NotNull(message = "start_date must be not null")
    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("end_date")
    private Long endDate;

    @NotNull(message = "price must be not null")
    @DecimalMin(value = "0", inclusive = true, message = "price must be greater than or equal 0")
    private BigDecimal price;

    private String note;

}
