package com.byakko.service.product.dtos.purchase_order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class PurchaseOrderMinResponse {

    private String id;

    private String employee;

    private String supplier;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    private BigDecimal surcharge;

    private BigDecimal tax;

    @JsonProperty("delivery_charge")
    private BigDecimal deliveryCharge;

    @JsonProperty("total_due")
    private BigDecimal totalDue;

    @JsonProperty("order_date")
    private Long orderDate;

    @JsonProperty("ship_date")
    private Long shipDate;

    private String note;

    private String status;

}
