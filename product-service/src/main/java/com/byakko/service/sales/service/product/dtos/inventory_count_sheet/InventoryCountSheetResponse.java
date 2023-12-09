package com.byakko.service.sales.service.product.dtos.inventory_count_sheet;

import com.byakko.service.sales.service.product.dtos.assets.AssetMinResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class InventoryCountSheetResponse {

    private String id;

    private String creator;

    private String implementer;

    private List<AssetMinResponse> asset;

    private String note;

    @JsonProperty("inventory_count_sheet_details")
    private List<InventoryCountSheetDetailResponse> inventoryCountSheetDetails;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("implement_time")
    private Long implementTime;

}
