package com.tejko.interfaces.api.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;

import com.tejko.models.general.payload.requests.MessageRequest;

public interface SocketControllerInterface {

    @MessageMapping("/greet")
    @SendTo("/topic/greetings")
    public ResponseEntity<?> greet(MessageRequest messageRequest, Principal principal);

    @MessageMapping("/chat")
    @SendToUser("/topic/user")
    public ResponseEntity<?> sendMessage(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId);

    @MessageMapping("/challenge")
    @SendToUser("/topic/challenge")
    public ResponseEntity<?> challenge(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId);

    @MessageMapping("/accept")
    @SendToUser("/topic/challenge")
    public ResponseEntity<?> accept(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId);

}
