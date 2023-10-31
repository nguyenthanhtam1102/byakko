package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.service.authentication.domain.domaincore.valueobject.MenuId;
import com.byakko.service.authentication.domain.domaincore.valueobject.ShopOwnerStatus;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Menu extends BaseEntity<MenuId> {
    private String name;
    public Menu(MenuId id, String name) {
        super(id);
        this.name = name;
    }
    public Menu(){

    }
    public void initialize(){
        setId(new MenuId(Integer.parseInt(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond()))));
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static final class Builder {
        private MenuId menuId;
        private String name;
        public static Menu.Builder builder() {
            return new Builder();
        }


        public Builder menuId(String menuId){
            this.menuId = new MenuId(Integer.parseInt(menuId));
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();
            menu.setName(name);
            menu.setId(menuId);
            return menu;
        }
    }
}
