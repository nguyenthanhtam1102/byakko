package com.byakko.common.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class ListAllCommand {

    @Min(value = 0, message = "page must be greater than or equal zero")
    private Integer page = 0;

    @Min(value = 1, message = "limit must be greater than 0")
    @Max(value = 100, message = "limit must be less than 100")
    private Integer limit = 15;

    private String query = "";

    @JsonProperty("sort_by")
    private String sortBy;

    @JsonProperty("sort_direction")
    private String sortDirection;

}
