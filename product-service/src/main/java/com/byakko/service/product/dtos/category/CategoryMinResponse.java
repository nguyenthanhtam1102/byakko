package com.byakko.service.product.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CategoryMinResponse {

    private String id;
    private String name;
    private List<CategoryMinResponse> children;

}
