package com.tejko.models.general.payload.responses;

import com.tejko.constants.TejkoConstants;
import com.tejko.models.general.enums.MessageType;

public class MessageResponse {

    private String subject;

    private String sender = TejkoConstants.DEFAULT_SENDER;

    private MessageType type = MessageType.DEFAULT;

    private Object content;

    public MessageResponse(String subject) {
        this.subject = subject;
    }

    public MessageResponse(String subject, Object content) {
        this.subject = subject;
        this.content = content;
    }

    public MessageResponse(String subject, MessageType type) {
        this.subject = subject;
        this.type = type;
    }

    public MessageResponse(String subject, MessageType type, Object content) {
        this.subject = subject;
        this.type = type;
        this.content = content;
    }

    public MessageResponse(String subject, MessageType type, Object content, String sender) {
        this.subject = subject;
        this.sender = sender;
        this.type = type;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}