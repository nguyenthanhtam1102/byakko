package com.byakko.service.production.dataaccess.repository;

import com.byakko.service.production.dataaccess.entity.GlobalOptionValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOptionValueJpaRepository extends JpaRepository<GlobalOptionValueEntity, String> {
}
