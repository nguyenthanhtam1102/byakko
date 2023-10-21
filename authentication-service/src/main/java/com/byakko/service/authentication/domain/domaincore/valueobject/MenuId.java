package com.byakko.service.authentication.domain.domaincore.valueobject;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.valueobject.BaseId;

public class MenuId extends BaseId<Integer> {
    public MenuId(Integer value){
        super(value);
    }
}
