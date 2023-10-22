package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, String>, JpaSpecificationExecutor<RoleEntity> {
}
