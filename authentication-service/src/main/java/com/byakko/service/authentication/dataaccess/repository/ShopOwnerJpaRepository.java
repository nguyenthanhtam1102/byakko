package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.ShopOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOwnerJpaRepository extends JpaRepository<ShopOwnerEntity, String> {
}
