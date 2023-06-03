package com.tejko.factories;

import org.springframework.stereotype.Component;
import com.tejko.interfaces.factories.MessageFactoryInterface;
import com.tejko.models.general.Message;
import com.tejko.models.general.payload.requests.MessageRequest;

@Component
public class MessageFactory implements MessageFactoryInterface {

    @Override
    public Message getObject(MessageRequest objectRequest) {
        Message message = Message.create(objectRequest.getSender(), objectRequest.getReceiver(), objectRequest.getType(), objectRequest.getContent());
        return message;
    }

}
