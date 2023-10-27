package com.byakko.service.production.domain.domainapplication.mapper;

import com.byakko.service.production.domain.domainapplication.dto.OptionResponse;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;

public class OptionMapper {

    public static OptionResponse toOptionResponse(GlobalOption option) {
        return OptionResponse.Builder.builder()
                .id(option.getId().getValue())
                .name(option.getName())
                .values(option.getValues().stream().map(OptionValueMapper::toOptionValueResponse).toList())
                .build();
    }

}
