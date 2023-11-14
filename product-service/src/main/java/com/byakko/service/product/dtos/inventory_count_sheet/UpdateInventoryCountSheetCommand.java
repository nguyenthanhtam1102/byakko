package com.byakko.service.product.dtos.inventory_count_sheet;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateInventoryCountSheetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
