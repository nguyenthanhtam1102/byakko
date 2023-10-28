package com.byakko.service.production.dataaccess.repository;

import com.byakko.service.production.dataaccess.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetJpaRepository extends JpaRepository<AssetEntity, String> {
}
