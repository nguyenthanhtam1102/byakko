package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.service.authentication.domain.domainapplication.dto.role.GetRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class GetRoleCommandHandler {

    private final RoleCommandHandlerHelper roleCommandHandlerHelper;

    public GetRoleCommandHandler(RoleCommandHandlerHelper roleCommandHandlerHelper) {
        this.roleCommandHandlerHelper = roleCommandHandlerHelper;
    }

    public RoleResponse get(GetRoleCommand command) {
        Role role = roleCommandHandlerHelper.findRoleById(command.getId());
        return RoleMapper.toRoleResponse(role);
    }

}
