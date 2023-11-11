package com.byakko.service.product.dtos.global_option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class OptionResponse {

    private String id;
    private String name;
    private Map<String, String> values;

}
