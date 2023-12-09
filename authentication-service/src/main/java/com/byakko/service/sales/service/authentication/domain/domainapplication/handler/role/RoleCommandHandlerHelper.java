package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.role;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandHandlerHelper {

    private final RoleRepository roleRepository;

    public RoleCommandHandlerHelper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role %s not found", id)));
    }

}
