package com.byakko.service.sales.service.authentication.domain.domaincore.entity;

import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.UserId;

public abstract class User extends BaseEntity<UserId> {
    public User() {
    }

    public User(UserId id) {
        super(id);
    }
}
