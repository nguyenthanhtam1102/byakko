package com.byakko.service.production.domain.domainapplication.mapper;

import com.byakko.service.production.domain.domainapplication.dto.OptionValueResponse;
import com.byakko.service.production.domain.domaincore.entity.GlobalOptionValue;

public class OptionValueMapper {

    public static OptionValueResponse toOptionValueResponse(GlobalOptionValue optionValue) {
        return OptionValueResponse.Builder.builder()
                .id(optionValue.getId().getValue())
                .name(optionValue.getName())
                .optionId(optionValue.getOption().getId().getValue())
                .build();
    }

}
