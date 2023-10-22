package com.byakko.service.authentication.dataaccess.repository;

import com.byakko.service.authentication.dataaccess.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByHashedData(String hashedData);
    Integer countByUserId(String userId);

}
