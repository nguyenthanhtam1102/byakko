package com.byakko.service.sales.dtos.wish_list_item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WishListItemResponse {

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("item_id")
    private String itemId;

    @JsonProperty("product_id")
    private String productId;

}
