package com.tejko.interfaces.factories;

import com.tejko.models.general.Message;
import com.tejko.models.general.payload.requests.MessageRequest;

public interface MessageFactoryInterface {

    public Message getObject(MessageRequest objectRequest);
    
}
