package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.global_option.OptionResponse;
import com.byakko.service.product.models.GlobalOption;

import java.util.HashMap;
import java.util.Map;

public class OptionMapper {

    public static OptionResponse toOptionResponse(GlobalOption option) {
        Map<String, String> values;
        if(option.getValues() != null && !option.getValues().isEmpty()) {
            values = new HashMap<>();
            option.getValues().forEach(value -> {
                values.put(value.getId(), value.getName());
            });
        } else {
            values = null;
        }

        return OptionResponse.builder()
                .id(option.getId())
                .name(option.getName())
                .values(values)
                .build();
    }

}
