package com.byakko.service.sales.service.authentication.domain.domainapplication.mapper;

import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role.CreateRoleCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Role;

public class RoleMapper {

    public static Role toRole(CreateRoleCommand command) {
        return Role.Builder.builder()
                .name(command.getName())
                .build();
    }

    public static RoleResponse toRoleResponse(Role role) {
        return RoleResponse.Builder.builder()
                .id(role.getId().getValue().toString())
                .name(role.getName())
                .menuId(role.getMenu() != null ? role.getMenu().getId().getValue().toString() : null)
                .build();
    }

}
