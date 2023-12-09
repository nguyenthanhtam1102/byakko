package com.byakko.service.sales.service.production.dataaccess.repository;

import com.byakko.service.sales.service.production.dataaccess.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetJpaRepository extends JpaRepository<AssetEntity, String> {
}
