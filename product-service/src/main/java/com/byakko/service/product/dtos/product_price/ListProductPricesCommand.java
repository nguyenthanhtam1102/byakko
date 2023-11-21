package com.byakko.service.product.dtos.product_price;

import com.byakko.common.application.dto.ListAllCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ListProductPricesCommand extends ListAllCommand {

    @JsonProperty("product_id")
    @NotBlank(message = "product_id must be not blank")
    private String productId;

}
