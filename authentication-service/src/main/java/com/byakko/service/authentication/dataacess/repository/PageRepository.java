package com.byakko.service.authentication.dataacess.repository;

import com.byakko.service.authentication.dataacess.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PageRepository extends JpaRepository<Page,Integer> {
    List<Page> findAll();
}
