package com.byakko.service.sales.service.product.dtos.purchase_order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class PurchaseOrderDetailResponse {

    private String id;

    private Product product;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("order_qty")
    private Integer orderQuantity;

    @JsonProperty("line_total")
    private BigDecimal lineTotal;

    @JsonProperty("received_qty")
    private Integer receivedQuantity;

    @JsonProperty("rejected_qty")
    private Integer rejectedQuantity;

    @JsonProperty("stocked_qty")
    private Integer stockedQuantity;

    private String note;

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
