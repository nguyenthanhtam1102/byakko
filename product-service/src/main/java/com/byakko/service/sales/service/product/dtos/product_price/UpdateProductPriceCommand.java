package com.byakko.service.sales.service.product.dtos.product_price;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class UpdateProductPriceCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String employee;

    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("end_date")
    private Long endDate;

    private BigDecimal price;
    private String note;
    private Boolean active;

}
