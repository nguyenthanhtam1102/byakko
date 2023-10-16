package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, String> {
}
