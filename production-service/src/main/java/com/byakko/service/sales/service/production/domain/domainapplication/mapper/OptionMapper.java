package com.byakko.service.sales.service.production.domain.domainapplication.mapper;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.OptionResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOption;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Option;

public class OptionMapper {

    public static OptionResponse toOptionResponse(GlobalOption option) {
        return OptionResponse.Builder.builder()
                .id(option.getId().getValue())
                .name(option.getName())
                .values(option.getValues().stream().map(OptionValueMapper::toOptionValueResponse).toList())
                .build();
    }

    public static OptionResponse toOptionResponse(Option option) {
        return OptionResponse.Builder.builder()
                .id(option.getId().getValue())
                .name(option.getName())
                .values(option.getValues().stream().map(OptionValueMapper::toOptionValueResponse).toList())
                .build();
    }

}
