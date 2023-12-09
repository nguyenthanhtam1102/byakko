package com.byakko.service.sales.service.authentication.dataaccess.repository;

import com.byakko.service.sales.service.authentication.dataaccess.entity.ShopOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopOwnerJpaRepository extends JpaRepository<ShopOwnerEntity, String> {
    @Query("select s from ShopOwnerEntity s where (s.phone = :phone or s.email = :email) and s.status != 'DELETED'")
    Optional<ShopOwnerEntity> findByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);
}
