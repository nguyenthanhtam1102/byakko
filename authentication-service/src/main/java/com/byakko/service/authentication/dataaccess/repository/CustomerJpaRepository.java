package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, String> {

    @Query("select c from CustomerEntity c where c.id = :id and c.status != 'DELETED'")
    Optional<CustomerEntity> findById(String id);

    Optional<CustomerEntity> findByPhoneOrEmail(String phone, String email);

    @Query("select c from CustomerEntity c where (c.phone = :phone or c.email = :email) and c.status != 'DELETED'")
    Optional<CustomerEntity> findByPhoneAndEmail(@Param("phone") String phone, @Param("email") String email);

}
