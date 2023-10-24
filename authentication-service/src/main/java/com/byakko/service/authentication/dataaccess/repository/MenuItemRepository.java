package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity,Integer> {
    @Query(name = "select * from menu_item where menu_id=:id",nativeQuery = true)
    List<MenuItemEntity> findByMenuId(@Param("id") int id);
    @Modifying
    @Transactional
    int deleteByMenu_Id(int id);
}
