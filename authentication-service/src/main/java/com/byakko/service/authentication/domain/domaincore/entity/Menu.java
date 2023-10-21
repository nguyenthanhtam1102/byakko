package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.service.authentication.domain.domaincore.valueobject.MenuId;

public class Menu extends BaseEntity<MenuId> {
    private String name;
    private Page page_id;
}
