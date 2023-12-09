package com.byakko.service.sales.service.product.dtos.assets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AssetResponse {

    private String id;
    private String filename;
    private String url;
    private Long size;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

}
