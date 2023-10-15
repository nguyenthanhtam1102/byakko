package com.byakko.service.authentication.domain.domainapplication;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.domain.domainapplication.dto.role.GetRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class GetRoleCommandHandler {

    private final RoleRepository roleRepository;

    public GetRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse get(GetRoleCommand command) {
        Role role = roleRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Role %d not found", command.getId())));
        return RoleMapper.toRoleResponse(role);
    }

}
