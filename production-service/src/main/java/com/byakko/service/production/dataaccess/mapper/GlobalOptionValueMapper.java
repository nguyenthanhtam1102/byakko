package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.GlobalOptionValueEntity;
import com.byakko.service.production.domain.domaincore.entity.GlobalOptionValue;

public class GlobalOptionValueMapper {

    public static GlobalOptionValueEntity toGlobalOptionValueEntity(GlobalOptionValue value) {
        return GlobalOptionValueEntity.builder()
                .id(value.getId().getValue())
                .name(value.getName())
                .build();
    }

}
