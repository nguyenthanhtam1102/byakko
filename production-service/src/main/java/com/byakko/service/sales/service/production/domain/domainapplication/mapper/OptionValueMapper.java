package com.byakko.service.sales.service.production.domain.domainapplication.mapper;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.OptionValueResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOptionValue;
import com.byakko.service.sales.service.production.domain.domaincore.entity.OptionValue;

public class OptionValueMapper {

    public static OptionValueResponse toOptionValueResponse(GlobalOptionValue optionValue) {
        return OptionValueResponse.Builder.builder()
                .id(optionValue.getId().getValue())
                .name(optionValue.getName())
                .optionId(optionValue.getOption().getId().getValue())
                .build();
    }

    public static OptionValueResponse toOptionValueResponse(OptionValue optionValue) {
        return OptionValueResponse.Builder.builder()
                .id(optionValue.getId().getValue())
                .name(optionValue.getName())
                .optionId(optionValue.getOption().getId().getValue())
                .build();
    }

}
