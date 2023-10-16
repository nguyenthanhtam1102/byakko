package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.role.UpdateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoleCommandHandler {

    private final RoleCommandHandlerHelper roleCommandHandlerHelper;
    private final RoleRepository roleRepository;

    public UpdateRoleCommandHandler(RoleCommandHandlerHelper roleCommandHandlerHelper, RoleRepository roleRepository) {
        this.roleCommandHandlerHelper = roleCommandHandlerHelper;
        this.roleRepository = roleRepository;
    }

    public RoleResponse update(UpdateRoleCommand command) {
        Role role = roleCommandHandlerHelper.findRoleById(command.getId());

        if(command.getName() != null)
            role.setName(command.getName());

        role.validate();
        roleRepository.save(role);

        return RoleMapper.toRoleResponse(role);
    }

}
