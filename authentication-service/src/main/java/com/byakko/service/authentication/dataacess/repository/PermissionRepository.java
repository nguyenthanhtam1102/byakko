package com.byakko.service.authentication.dataacess.repository;

import com.byakko.service.authentication.dataacess.entity.Permissions;
import com.byakko.service.authentication.domain.domainapplication.dto.response.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions,Integer> {
    List<Permissions> findByPermissionGroups_Id(int permissionGroupId);
}
