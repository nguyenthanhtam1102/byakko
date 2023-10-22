package com.byakko.common.domain.domainapplication;

import com.byakko.common.dataaccess.repositories.MessageJpaRepository;
import com.byakko.common.domain.domainapplication.ports.output.repositories.MessageRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageManagerImpl implements MessageManager {

    private final Map<String, String> messages;

    public MessageManagerImpl(MessageRepository messageRepository, MessageJpaRepository messageJpaRepository) {
        this.messages = new HashMap<>();
        messageJpaRepository.findAll()
                .forEach(message -> messages.put(message.getKey(), message.getValue()));
    }

    @Override
    public String getMessage(String key) {
        String result = messages.get(key);
        return result == null ? "" : result;
    }

}
