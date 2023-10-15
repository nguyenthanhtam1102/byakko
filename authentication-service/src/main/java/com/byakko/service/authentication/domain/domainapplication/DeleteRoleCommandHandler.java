package com.byakko.service.authentication.domain.domainapplication;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.role.DeleteRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoleCommandHandler {

    private final RoleRepository roleRepository;

    public DeleteRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void delete(DeleteRoleCommand command) {
        Role role = roleRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Role %d not found", command.getId())));
        roleRepository.delete(role);
    }

}
