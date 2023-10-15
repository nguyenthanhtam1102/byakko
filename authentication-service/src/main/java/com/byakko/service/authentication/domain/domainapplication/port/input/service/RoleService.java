package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.role.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface RoleService {

    ListAllRoleResponse listAllRoles(@Valid ListAllRoleCommand command);
    RoleResponse getRole(@Valid GetRoleCommand command);
    RoleResponse createRole(@Valid CreateRoleCommand command);
    RoleResponse updateRole(@Valid UpdateRoleCommand command);
    void deleteRole(@Valid DeleteRoleCommand command);

}
