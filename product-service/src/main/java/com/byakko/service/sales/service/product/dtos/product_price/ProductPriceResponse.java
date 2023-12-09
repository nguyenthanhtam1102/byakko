package com.byakko.service.sales.service.product.dtos.product_price;

import com.byakko.service.sales.service.product.dtos.MoneyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ProductPriceResponse {

    private String id;
    private String employee;
    private MoneyResponse price;

    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("end_date")
    private Long endDate;
    private String note;
    private Boolean active;

    @JsonProperty("modified_date")
    private Long modifiedDate;

    @Data
    @AllArgsConstructor
    @Builder
    public static class Product {

        private String id;
        private String barcode;
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
