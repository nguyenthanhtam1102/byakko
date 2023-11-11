package com.byakko.service.product.dtos.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private String id;
    private String name;
    private String description;

    private CategoryMinResponse parent;
    private List<CategoryMinResponse> children;

    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

}
