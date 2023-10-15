package com.byakko.service.authentication.dataaccess.adapter;

import com.byakko.service.authentication.dataaccess.entity.RoleEntity;
import com.byakko.service.authentication.dataaccess.mapper.RoleMapper;
import com.byakko.service.authentication.dataaccess.repository.RoleJpaRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.RoleRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Page<Role> findAll(Integer page, Integer limit) {
        return roleJpaRepository.findAll(PageRequest.of(page, limit)).map(RoleMapper::toRole);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id).map(RoleMapper::toRole);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleJpaRepository.save(RoleMapper.toRoleEntity(role));
        return RoleMapper.toRole(roleEntity);
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.deleteById(role.getId().getValue());
    }
}
