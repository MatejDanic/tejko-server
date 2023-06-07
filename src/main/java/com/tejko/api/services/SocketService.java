package com.tejko.api.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tejko.components.JwtComponent;
import com.tejko.factories.MessageFactory;
import com.tejko.interfaces.api.services.SocketServiceInterface;
import com.tejko.models.general.Message;
import com.tejko.models.general.payload.requests.MessageRequest;

@Service
public class SocketService implements SocketServiceInterface {
    
    @Autowired
    JwtComponent jwtComponent;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    MessageFactory messageFactory;

    private Map<String, String> userMap = new HashMap<>();

    @Override
    public void greet(MessageRequest messageRequest, Principal principal) {
        validateMessageRequest(messageRequest);
        addUUID(messageRequest.getSender(), principal.getName());
    }

    @Override
    public void sendMessage(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        Message message = messageFactory.getObject(messageRequest);
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/user", message);
    }

    @Override
    public void challenge(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        Message message = messageFactory.getObject(messageRequest);
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/challenge", message);
    }

    @Override
    public void accept(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        Message message = messageFactory.getObject(messageRequest);
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/accept", message);
    }

    private void validateMessageRequest(MessageRequest messageRequest) {
        //TODO: implement message request validation
    }
    
    private void addUUID(String username, String uuid) {
        userMap.put(username, uuid);
    }

    private String getUUIDFromUsername(String username) {
        String uuid = "";
        for (Entry<String, String> entry : userMap.entrySet()) {
            if (entry.getKey().equals(username)) {
                uuid = entry.getValue();
            }
        }
        return uuid;
    }

}