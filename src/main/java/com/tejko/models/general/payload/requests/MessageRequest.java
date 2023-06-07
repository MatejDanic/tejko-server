package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.enums.MessageType;

public class MessageRequest {

    @NotBlank
    private MessageType type;

    @NotBlank
    private String sender;

    @NotBlank
    private String receiver;

    @NotBlank
    private Object content;

    public MessageType getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Object getContent() {
        return content;
    }

}