package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.service.authentication.domain.domainapplication.dto.role.CreateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class CreateRoleCommandHandler {

    private final RoleRepository roleRepository;
//    private final MenuRe

    public CreateRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse create(CreateRoleCommand command) {
        Role role = RoleMapper.toRole(command);

//        Menu menu

        role.initialize();
        role.validate();
        roleRepository.save(role);
        return RoleMapper.toRoleResponse(role);
    }

}
