package com.byakko.service.product.dtos.inventory_count_sheet;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllInventoryCountSheetResponse extends ListAllResponse<InventoryCountSheetMinResponse> {
}
