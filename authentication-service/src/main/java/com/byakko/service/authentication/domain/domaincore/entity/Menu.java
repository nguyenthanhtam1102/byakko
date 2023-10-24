package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.service.authentication.domain.domaincore.valueobject.MenuId;

public class Menu extends BaseEntity<MenuId> {
    private String name;

    public Menu(MenuId id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
