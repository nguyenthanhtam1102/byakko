package com.byakko.service.product.dtos.good_receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class GoodsReceiptDetailResponse {

    private String id;

    @JsonProperty("product_id")
    private Product product;

    @JsonProperty("order_qty")
    private Integer orderQuantity;

    @JsonProperty("received_qty")
    private Integer receivedQuantity;

    @JsonProperty("rejected_qty")
    private Integer rejectedQuantity;

    private String note;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @Data
    @AllArgsConstructor
    @Builder
    public static class Product {

        private String id;
        private String name;
        private ProductVariant variant;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ProductVariant {

        private String id;
        private Map<String, String> options;

    }

}
