package com.byakko.service.authentication.dataacess.repository;

import com.byakko.service.authentication.dataacess.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {
    List<Menu> findAll();
}
