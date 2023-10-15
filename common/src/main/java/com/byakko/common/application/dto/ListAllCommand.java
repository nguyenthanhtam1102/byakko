package com.byakko.common.application.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class ListAllCommand {

    @NotNull(message = "page must be not null")
    @Min(value = 0, message = "page must be greater than or equal zero")
    private Integer page;

    @NotNull(message = "limit must be not null")
    @Min(value = 1, message = "limit must be greater than 0")
    @Max(value = 100, message = "limit must be less than 100")
    private Integer limit;

}
