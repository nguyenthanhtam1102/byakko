package com.byakko.service.sales.service.product.dtos.inventory_count_sheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class InventoryCountCommand {

    @NotBlank(message = "inventory_count_sheet must be not blank")
    @JsonProperty("inventory_count_sheet")
    private String inventoryCountSheet;

    @NotBlank(message = "implementer must be not blank")
    private String implementer;

    private String note;

    @NotNull(message = "inventory_count_details must be not null")
    @NotEmpty(message = "inventory_count_details must be not empty")
    @JsonProperty("inventory_count_details")
    private Set<InventoryCountDetail> inventoryCountDetails;

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class InventoryCountDetail {

        @NotBlank(message = "inventory_count_sheet_detail must be not blank")
        @EqualsAndHashCode.Include
        @JsonProperty("inventory_count_sheet_detail")
        private String inventoryCountSheetDetail;

        @NotNull(message = "physical_inventory must be not null")
        @Min(value = 0, message = "physical_inventory must be greater than or equal 0")
        @JsonProperty("physical_inventory")
        private Integer physicalInventory;

        private String note;

    }

}
