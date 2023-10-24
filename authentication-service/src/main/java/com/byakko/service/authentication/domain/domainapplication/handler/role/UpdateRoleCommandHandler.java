package com.byakko.service.authentication.domain.domainapplication.handler.role;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.dataaccess.repository.MenuJpaRepository;
import com.byakko.service.authentication.domain.domainapplication.dto.role.RoleResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.role.UpdateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.mapper.MenuMapper;
import com.byakko.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.MenuRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoleCommandHandler {

    private final RoleCommandHandlerHelper roleCommandHandlerHelper;
    private final RoleRepository roleRepository;
    private final MenuJpaRepository menuRepository;
    public UpdateRoleCommandHandler(RoleCommandHandlerHelper roleCommandHandlerHelper, RoleRepository roleRepository, MenuJpaRepository menuRepository) {
        this.roleCommandHandlerHelper = roleCommandHandlerHelper;
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
    }

    public RoleResponse update(UpdateRoleCommand command) {
        Role role = roleCommandHandlerHelper.findRoleById(command.getId());

        if(command.getName() != null)
            role.setName(command.getName());

        MenuEntity menu = menuRepository.findById(command.getMenuID()).get();
        if(menu == null){
            throw new NotFoundException("Can not find Menu");
        }
        role.setMenu(MenuMapper.toMenu(menu));
        role.validate();
        roleRepository.save(role);

        return RoleMapper.toRoleResponse(role);
    }

}
