package com.byakko.service.sales.service.authentication.domain.domainapplication.mapper;

import com.byakko.service.sales.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.MenuId;

public class MenuMapper {
    public static Menu toMenu(MenuEntity menu) {
        return new Menu(new MenuId(menu.getId()), menu.getName());
    }
    public static MenuEntity toMenuEntity(Menu menu){
        return MenuEntity.builder()
                .id(menu.getId().getValue())
                .name(menu.getName())
                .build();
    }
}
