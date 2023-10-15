package com.byakko.service.authentication.dataaccess.mapper;

import com.byakko.service.authentication.dataaccess.entity.RoleEntity;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import com.byakko.service.authentication.domain.domaincore.valueobject.RoleId;

public class RoleMapper {

    public static RoleEntity toRoleEntity(Role role) {
        return RoleEntity.builder()
                .id(role.getId().getValue())
                .name(role.getName())
                .build();
    }

    public static Role toRole(RoleEntity role) {
        return Role.Builder.builder()
                .id(new RoleId(role.getId()))
                .name(role.getName())
                .build();
    }

}
