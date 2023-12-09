package com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository {

    Page<Role> findAll(Integer page, Integer limit, String query);
    Optional<Role> findById(String id);
    Role save(Role role);
    void delete(Role role);

}
