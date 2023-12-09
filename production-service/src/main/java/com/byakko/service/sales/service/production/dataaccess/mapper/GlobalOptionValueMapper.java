package com.byakko.service.sales.service.production.dataaccess.mapper;

import com.byakko.service.sales.service.production.dataaccess.entity.GlobalOptionValueEntity;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOptionValue;

public class GlobalOptionValueMapper {

    public static GlobalOptionValueEntity toGlobalOptionValueEntity(GlobalOptionValue value) {
        return GlobalOptionValueEntity.builder()
                .id(value.getId().getValue())
                .name(value.getName())
                .build();
    }

}
