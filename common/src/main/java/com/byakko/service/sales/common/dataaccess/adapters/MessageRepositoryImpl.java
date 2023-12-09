package com.byakko.service.sales.common.dataaccess.adapters;

import com.byakko.service.sales.common.domain.domainapplication.ports.output.repositories.MessageRepository;
import com.byakko.service.sales.common.domain.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

//    private final MessageJpaRepository messageJpaRepository;

    @Override
    public List<Message> findAll() {
//        return messageJpaRepository.findAll().stream().map(MessageMapper::toMessage).toList();
        return null;
    }
}
