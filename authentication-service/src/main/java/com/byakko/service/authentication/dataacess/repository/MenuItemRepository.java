package com.byakko.service.authentication.dataacess.repository;

import com.byakko.service.authentication.dataacess.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    @Query(name = "select * from menu_item where menu_id=:id",nativeQuery = true)
    List<MenuItem> findByMenuId(@Param("id") int id);
    @Modifying
    @Transactional
    int deleteByMenu_Id(int id);
}
