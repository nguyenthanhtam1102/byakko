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
public class GoodsReceiptResponse {

    private String id;

    @JsonProperty("purchase_order")
    private String purchaseOrder;

    private String employee;

    private List<AssetMinResponse> asset;

    @JsonProperty("goods_receipt_details")
    private List<GoodsReceiptDetailResponse> goodsReceiptDetails;

    private String note;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

}
