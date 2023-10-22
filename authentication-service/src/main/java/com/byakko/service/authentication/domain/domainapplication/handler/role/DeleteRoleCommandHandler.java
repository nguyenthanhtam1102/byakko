package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.service.authentication.domain.domainapplication.dto.role.DeleteRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoleCommandHandler {

    private final RoleCommandHandlerHelper roleCommandHandlerHelper;
    private final RoleRepository roleRepository;

    public DeleteRoleCommandHandler(RoleCommandHandlerHelper roleCommandHandlerHelper, RoleRepository roleRepository) {
        this.roleCommandHandlerHelper = roleCommandHandlerHelper;
        this.roleRepository = roleRepository;
    }

    public void delete(DeleteRoleCommand command) {
        Role role = roleCommandHandlerHelper.findRoleById(command.getId());
        roleRepository.delete(role);
    }

}
