package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
}
