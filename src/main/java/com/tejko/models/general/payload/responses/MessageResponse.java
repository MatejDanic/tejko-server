package com.tejko.models.general.payload.responses;

import com.tejko.models.general.Message;

public class MessageResponse extends ApiResponse<Message> {

    public MessageResponse(Message object) {
        super(object);
    }

}