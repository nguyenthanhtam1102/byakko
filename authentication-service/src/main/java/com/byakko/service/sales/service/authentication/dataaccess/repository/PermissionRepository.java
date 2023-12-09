package com.byakko.service.sales.service.authentication.dataaccess.repository;

import com.byakko.service.sales.service.authentication.dataaccess.entity.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionsEntity,Integer> {
    List<PermissionsEntity> findByPermissionGroups_Id(int permissionGroupId);
}
