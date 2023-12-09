package com.byakko.service.sales.common.dataaccess.mappers;

import com.byakko.service.sales.common.dataaccess.entity.MessageEntity;
import com.byakko.service.sales.common.domain.entity.Message;

public class MessageMapper {

    public static Message toMessage(MessageEntity message) {
        return new Message(message.getKey(), message.getValue());
    }

}
