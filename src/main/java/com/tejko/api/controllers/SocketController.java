package com.tejko.api.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.SocketService;
import com.tejko.api.services.UserService;
import com.tejko.interfaces.api.controllers.SocketControllerInterface;
import com.tejko.models.general.payload.requests.MessageRequest;

@RestController
public class SocketController implements SocketControllerInterface {

    @Autowired
    UserService userService;

    @Autowired
    SocketService socketService;

    @MessageMapping("/greet")
    @SendTo("/topic/greetings")
    @Override
    public ResponseEntity<?> greet(MessageRequest messageRequest, Principal principal) {
        socketService.greet(messageRequest, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/chat")
    @SendToUser("/topic/user")
    @Override
    public ResponseEntity<?> sendMessage(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId) {
        socketService.sendMessage(messageRequest, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/challenge")
    @SendToUser("/topic/challenge")
    @Override
    public ResponseEntity<?> challenge(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId) {
        socketService.challenge(messageRequest, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/accept")
    @SendToUser("/topic/accept")
    @Override
    public ResponseEntity<?> accept(MessageRequest messageRequest, String sessionId) {
        socketService.accept(messageRequest, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}