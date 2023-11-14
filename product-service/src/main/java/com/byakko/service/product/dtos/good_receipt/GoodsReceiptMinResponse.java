package com.byakko.service.product.dtos.good_receipt;

import com.byakko.service.product.dtos.assets.AssetMinResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GoodsReceiptMinResponse {

    private String id;

    @JsonProperty("purchase_order")
    private String purchaseOrder;

    private String employee;

    private List<AssetMinResponse> asset;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

}
