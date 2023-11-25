package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetOrderCommand {

    @NotBlank(message = "order_id")
    @JsonProperty("order_id")
    private String orderId;

}
