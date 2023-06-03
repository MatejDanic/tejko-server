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
import com.tejko.models.general.payload.requests.MessageRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.MessageResponse;

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
    public ApiResponse<?> greet(MessageRequest messageRequest, Principal principal) {
        validateMessageRequest(messageRequest);
        addUUID(messageRequest.getSender(), principal.getName());
        return new ApiResponse<>("Greeting sent successfully.");
    }

    @Override
    public ApiResponse<?> sendMessage(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        MessageResponse response = new MessageResponse(messageFactory.getObject(messageRequest));
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/user", response);
        return new ApiResponse<>("Message sent successfully.");
    }

    @Override
    public ApiResponse<?> challenge(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        MessageResponse response = new MessageResponse(messageFactory.getObject(messageRequest));
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/challenge", response);
        return new ApiResponse<>("Challenge made successfully.");
    }

    @Override
    public ApiResponse<?> accept(MessageRequest messageRequest, String sessionId) {
        validateMessageRequest(messageRequest);
        MessageResponse response = new MessageResponse(messageFactory.getObject(messageRequest));
        simpMessagingTemplate.convertAndSendToUser(getUUIDFromUsername(messageRequest.getReceiver()), "/topic/accept", response);
        return new ApiResponse<>("Challenge made successfully.");
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