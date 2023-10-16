package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandHandlerHelper {

    private final RoleCommandHandlerHelper roleCommandHandlerHelper;

    public RoleCommandHandlerHelper(RoleCommandHandlerHelper roleCommandHandlerHelper) {
        this.roleCommandHandlerHelper = roleCommandHandlerHelper;
    }

    public Role findRoleById(String id) {
        return roleCommandHandlerHelper.findRoleById(id);
    }

}
