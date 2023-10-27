package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.GlobalOptionEntity;
import com.byakko.service.production.dataaccess.entity.GlobalOptionValueEntity;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import com.byakko.service.production.domain.domaincore.entity.GlobalOptionValue;
import com.byakko.service.production.domain.domaincore.valueobject.GlobalOptionId;
import com.byakko.service.production.domain.domaincore.valueobject.GlobalOptionValueId;

import java.util.stream.Collectors;

public class GlobalOptionMapper {

    public static GlobalOptionEntity toGlobalOptionEntity(GlobalOption option) {
        GlobalOptionEntity optionEntity = GlobalOptionEntity.builder()
                .id(option.getId().getValue())
                .name(option.getName())
                .build();

        if(option.getValues() != null) {
            optionEntity.setValues(
                    option.getValues().stream().map(optionValue ->
                            GlobalOptionValueEntity.builder()
                                    .id(optionValue.getId().getValue())
                                    .name(optionValue.getName())
                                    .option(optionEntity)
                                    .build()
                    ).collect(Collectors.toSet())
            );
        }

        return optionEntity;
    }

    public static GlobalOption toGlobalOption(GlobalOptionEntity optionEntity) {
        GlobalOption option = GlobalOption.Builder.builder()
                .id(new GlobalOptionId(optionEntity.getId()))
                .name(optionEntity.getName())
                .build();

        if(optionEntity.getValues() != null && !optionEntity.getValues().isEmpty()) {
            option.setValues(
                    optionEntity.getValues().stream().map(valueEntity ->
                            GlobalOptionValue.Builder.builder()
                                    .id(new GlobalOptionValueId(valueEntity.getId()))
                                    .name(valueEntity.getName())
                                    .option(option)
                                    .build()
                    ).collect(Collectors.toSet())
            );
        }

        return option;
    }

}
