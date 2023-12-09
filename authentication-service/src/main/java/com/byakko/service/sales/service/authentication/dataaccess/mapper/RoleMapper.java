package com.byakko.service.sales.service.authentication.dataaccess.mapper;

import com.byakko.service.sales.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.sales.service.authentication.dataaccess.entity.RoleEntity;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Role;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.MenuId;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.RoleId;

public class RoleMapper {

    public static RoleEntity toRoleEntity(Role role) {
        if(role.getMenu()==null){
            return RoleEntity.builder()
                    .id(role.getId().getValue())
                    .name(role.getName())
                    .build();
        }
        return RoleEntity.builder()
                .id(role.getId().getValue())
                .name(role.getName())
                .menu(new MenuEntity(role.getMenu().getId().getValue()))
                .build();
    }

    public static Role toRole(RoleEntity role) {
        if(role.getMenu()==null){
            return Role.Builder.builder()
                    .id(new RoleId(role.getId()))
                    .name(role.getName())
                    .build();
        }
        return Role.Builder.builder()
                .id(new RoleId(role.getId()))
                .name(role.getName())
                .menu(new Menu(new MenuId(role.getMenu().getId()),role.getName()))
                .build();
    }
}
