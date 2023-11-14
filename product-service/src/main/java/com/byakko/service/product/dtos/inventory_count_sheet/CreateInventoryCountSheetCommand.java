package com.byakko.service.product.dtos.inventory_count_sheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CreateInventoryCountSheetCommand {

    @NotBlank(message = "creator must be not blank")
    private String creator;

    private String note;

    @NotNull(message = "inventory_count_sheet_details must be not null")
    @NotEmpty(message = "inventory_count_sheet_details must be not empty")
    @JsonProperty("inventory_count_sheet_details")
    private Set<CreateInventoryCountSheetDetailCommand> inventoryCountSheetDetails;

}
