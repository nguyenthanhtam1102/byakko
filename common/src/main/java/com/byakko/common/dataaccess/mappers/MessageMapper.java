package com.byakko.common.dataaccess.mappers;

import com.byakko.common.dataaccess.entity.MessageEntity;
import com.byakko.common.domain.entity.Message;

public class MessageMapper {

    public static Message toMessage(MessageEntity message) {
        return new Message(message.getKey(), message.getValue());
    }

}
