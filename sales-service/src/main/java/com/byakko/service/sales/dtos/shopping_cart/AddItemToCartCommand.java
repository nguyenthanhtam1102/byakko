package com.byakko.service.sales.dtos.shopping_cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AddItemToCartCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "product_id must be not blank")
    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("variant_id")
    private String variantId;

    @Min(value = 1, message = "quantity must be greater than 0")
    private int quantity = 1;

}
