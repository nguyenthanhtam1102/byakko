package com.byakko.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.dto.role.*;
import com.byakko.service.authentication.domain.domainapplication.handler.role.*;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    private final ListAllRoleCommandHandler listAllRoleCommandHandler;
    private final GetRoleCommandHandler getRoleCommandHandler;
    private final CreateRoleCommandHandler createRoleCommandHandler;
    private final UpdateRoleCommandHandler updateRoleCommandHandler;
    private final DeleteRoleCommandHandler deleteRoleCommandHandler;

    public RoleServiceImpl(ListAllRoleCommandHandler listAllRoleCommandHandler,
                           GetRoleCommandHandler getRoleCommandHandler,
                           CreateRoleCommandHandler createRoleCommandHandler,
                           UpdateRoleCommandHandler updateRoleCommandHandler,
                           DeleteRoleCommandHandler deleteRoleCommandHandler) {
        this.listAllRoleCommandHandler = listAllRoleCommandHandler;
        this.getRoleCommandHandler = getRoleCommandHandler;
        this.createRoleCommandHandler = createRoleCommandHandler;
        this.updateRoleCommandHandler = updateRoleCommandHandler;
        this.deleteRoleCommandHandler = deleteRoleCommandHandler;
    }

    @Override
    public ListAllRoleResponse listAllRoles(ListAllRoleCommand command) {
        return listAllRoleCommandHandler.listAll(command);
    }

    @Override
    public RoleResponse getRole(GetRoleCommand command) {
        return getRoleCommandHandler.get(command);
    }

    @Override
    public RoleResponse createRole(CreateRoleCommand command) {
        return createRoleCommandHandler.create(command);
    }

    @Override
    public RoleResponse updateRole(UpdateRoleCommand command) {
        return updateRoleCommandHandler.update(command);
    }

    @Override
    public void deleteRole(DeleteRoleCommand command) {
        deleteRoleCommandHandler.delete(command);
    }


}
