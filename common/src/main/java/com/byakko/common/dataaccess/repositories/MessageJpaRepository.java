package com.byakko.common.dataaccess.repositories;

import com.byakko.common.dataaccess.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Integer> {
}
