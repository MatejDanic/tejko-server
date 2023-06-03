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
import com.tejko.models.general.payload.responses.ApiResponse;

@RestController
public class SocketController implements SocketControllerInterface {

    @Autowired
    UserService userService;

    @Autowired
    SocketService socketService;

    @MessageMapping("/greet")
    @SendTo("/topic/greetings")
    @Override
    public ResponseEntity<ApiResponse<?>> greet(MessageRequest messageRequest, Principal principal) {
        return new ResponseEntity<>(socketService.greet(messageRequest, principal), HttpStatus.OK);
    }

    @MessageMapping("/chat")
    @SendToUser("/topic/user")
    @Override
    public ResponseEntity<ApiResponse<?>> sendMessage(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId) {
        return new ResponseEntity<>(socketService.sendMessage(messageRequest, sessionId), HttpStatus.OK);
    }

    @MessageMapping("/challenge")
    @SendToUser("/topic/challenge")
    @Override
    public ResponseEntity<ApiResponse<?>> challenge(@Payload MessageRequest messageRequest, @Header("simpSessionId") String sessionId) {
        return new ResponseEntity<>(socketService.challenge(messageRequest, sessionId), HttpStatus.OK);
    }

    @MessageMapping("/accept")
    @SendToUser("/topic/accept")
    @Override
    public ResponseEntity<ApiResponse<?>> accept(MessageRequest messageRequest, String sessionId) {
        return new ResponseEntity<>(socketService.accept(messageRequest, sessionId), HttpStatus.OK);
    }
}