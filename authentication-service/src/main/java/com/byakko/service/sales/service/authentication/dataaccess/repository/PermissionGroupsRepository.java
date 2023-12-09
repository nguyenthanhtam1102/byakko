package com.byakko.service.sales.service.authentication.dataaccess.repository;

import com.byakko.service.sales.service.authentication.dataaccess.entity.PermissionGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupsRepository extends JpaRepository<PermissionGroupsEntity,Integer> {
}
