package com.byakko.common.domain.domainapplication.ports.output.repositories;

import com.byakko.common.domain.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageRepository {

    List<Message> findAll();

}
