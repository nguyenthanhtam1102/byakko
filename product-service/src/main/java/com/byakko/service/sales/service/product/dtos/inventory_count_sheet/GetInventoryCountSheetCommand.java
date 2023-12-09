package com.byakko.service.sales.service.product.dtos.inventory_count_sheet;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GetInventoryCountSheetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

}
