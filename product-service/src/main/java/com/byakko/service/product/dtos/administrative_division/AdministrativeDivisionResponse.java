package com.byakko.service.product.dtos.administrative_division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdministrativeDivisionResponse {

    private String code;
    private String slug;
    private String name;

    @JsonProperty("name_with_type")
    private String nameWithType;

    private String path;

    @JsonProperty("path_with_type")
    private String pathWithType;

}
