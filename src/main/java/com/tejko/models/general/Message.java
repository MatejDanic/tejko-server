package com.tejko.models.general;

import com.tejko.models.general.enums.MessageType;

public class Message {

    private String sender;
    private String receiver;
    private MessageType type;
    private Object content;

    private Message(String sender, String receiver, MessageType type, Object content) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.content = content;
    }

    public static Message create(String sender, String receiver, MessageType type, Object content) {
        return new Message(sender, receiver, type, content);
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public MessageType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }


}