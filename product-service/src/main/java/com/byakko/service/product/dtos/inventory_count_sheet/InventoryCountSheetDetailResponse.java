package com.byakko.service.product.dtos.inventory_count_sheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class InventoryCountSheetDetailResponse {

    private String id;
    private Product product;

    @JsonProperty("book_inventory")
    private Integer bookInventory;

    @JsonProperty("physical_inventory")
    private Integer physicalInventory;

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
