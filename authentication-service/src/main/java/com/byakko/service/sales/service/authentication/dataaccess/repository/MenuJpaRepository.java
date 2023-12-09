package com.byakko.service.sales.service.authentication.dataaccess.repository;

import com.byakko.service.sales.service.authentication.dataaccess.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuJpaRepository extends JpaRepository<MenuEntity,Integer> {
    @Query("select m from MenuEntity m where m.id = :id")
    Optional<MenuEntity> findByMenuId(int id);
}
