package com.byakko.service.sales.dtos.wish_list_item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AddItemToWishListCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "product_id must be not blank")
    @JsonProperty("product_id")
    private String productId;

}
