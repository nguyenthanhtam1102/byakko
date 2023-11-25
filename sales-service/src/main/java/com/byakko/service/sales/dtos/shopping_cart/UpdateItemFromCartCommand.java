package com.byakko.service.sales.dtos.shopping_cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateItemFromCartCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "item_id must be not blank")
    @JsonProperty("item_id")
    private String itemId;

    @JsonProperty("variant_id")
    private String variantId;

    private Integer quantity;

}
