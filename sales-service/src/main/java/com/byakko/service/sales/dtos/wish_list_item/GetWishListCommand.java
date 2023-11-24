package com.byakko.service.sales.dtos.wish_list_item;

import com.byakko.common.application.dto.ListAllCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetWishListCommand extends ListAllCommand {

    @NotBlank(message = "customer_id")
    @JsonProperty("customer_id")
    private String customerId;

}
