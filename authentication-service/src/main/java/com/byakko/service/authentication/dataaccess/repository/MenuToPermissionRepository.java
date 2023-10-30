package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.MenuItemEntity;
import com.byakko.service.authentication.dataaccess.entity.MenusToPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MenuToPermissionRepository extends JpaRepository<MenusToPermissionsEntity,Integer> {
    @Modifying
    @Transactional
    @Query("delete from MenusToPermissionsEntity m where m.menuItem = :menuItem")
    void deleteByMenuItem(MenuItemEntity menuItem);
}
