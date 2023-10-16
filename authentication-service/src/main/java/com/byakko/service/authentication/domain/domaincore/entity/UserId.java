package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.valueobject.BaseId;

public class UserId extends BaseId<String> {
    protected UserId(String value) {
        super(value);
    }
}
