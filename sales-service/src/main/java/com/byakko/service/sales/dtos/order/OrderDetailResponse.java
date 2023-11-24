package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class OrderDetailResponse {

    private String id;
    private String product;
    private String variant;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    private Integer quantity;

}
