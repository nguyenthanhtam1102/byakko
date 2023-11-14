package com.byakko.service.product.dtos.inventory_count_sheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateInventoryCountSheetDetailCommand {

    @NotBlank(message = "product must be not blank")
    @EqualsAndHashCode.Include
    private String product;

    @NotBlank(message = "product_variant must be not blank")
    @JsonProperty("product_variant")
    @EqualsAndHashCode.Include
    private String productVariant;

    private String note;

}
