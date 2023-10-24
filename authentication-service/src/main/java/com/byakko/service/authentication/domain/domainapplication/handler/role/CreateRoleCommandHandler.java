package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.dataaccess.repository.MenuJpaRepository;
import com.byakko.service.authentication.domain.domainapplication.dto.role.CreateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.mapper.MenuMapper;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class CreateRoleCommandHandler {
    private final MenuJpaRepository menuRepository;
    private final RoleRepository roleRepository;
//    private final MenuRe

    public CreateRoleCommandHandler(MenuJpaRepository menuRepository, RoleRepository roleRepository) {
        this.menuRepository = menuRepository;
        this.roleRepository = roleRepository;
    }

    public RoleResponse create(CreateRoleCommand command) {
        Role role = RoleMapper.toRole(command);
//        Menu menu
        MenuEntity menu = menuRepository.findById(command.getMenuId()).get();
        if(menu == null){
            throw new NotFoundException("Can not find menu");
        }
        role.setMenu(MenuMapper.toMenu(menu));
        role.initialize();
        role.validate();
        roleRepository.save(role);
        return RoleMapper.toRoleResponse(role);
    }

}
