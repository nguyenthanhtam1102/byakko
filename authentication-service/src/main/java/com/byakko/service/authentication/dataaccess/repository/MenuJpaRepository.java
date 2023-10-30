package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuJpaRepository extends JpaRepository<MenuEntity,Integer> {
    @Query("select m from MenuEntity m where m.id = :id")
    Optional<MenuEntity> findByMenuId(int id);
}
