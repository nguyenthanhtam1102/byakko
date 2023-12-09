package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.role;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role.ListAllRoleCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role.ListAllRoleResponse;
import com.byakko.service.sales.service.authentication.domain.domainapplication.mapper.RoleMapper;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ListAllRoleCommandHandler {

    private final RoleRepository roleRepository;

    public ListAllRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ListAllRoleResponse listAll(ListAllRoleCommand command) {
        Page<Role> rolePage = roleRepository.findAll(command.getPage(), command.getLimit(), command.getQuery());
        return ListAllRoleResponse.builder()
                .data(rolePage.get().map(RoleMapper::toRoleResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(rolePage))
                .build();
    }

}
