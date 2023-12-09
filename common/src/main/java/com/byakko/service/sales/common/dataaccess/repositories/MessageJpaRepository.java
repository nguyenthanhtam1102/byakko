package com.byakko.service.sales.common.dataaccess.repositories;

import com.byakko.service.sales.common.dataaccess.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Integer> {
}
