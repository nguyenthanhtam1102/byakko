package com.byakko.service.authentication.dataacess.repository;

import com.byakko.service.authentication.dataacess.entity.MenusToPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MenuToPermissionRepository extends JpaRepository<MenusToPermissions,Integer> {
}
