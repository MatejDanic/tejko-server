package matej.tejko.api.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import matej.tejko.api.services.SocketService;
import matej.tejko.api.services.UserService;
import matej.tejko.components.JwtComponent;
import matej.tejko.models.general.enums.MessageType;
import matej.tejko.models.general.payload.requests.MessageRequest;
import matej.tejko.models.general.payload.responses.MessageResponse;

@RestController
public class SocketController {

    @Autowired
    UserService userService;

    @Autowired
    SocketService socketService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JwtComponent jwtComponent;

    @MessageMapping("/greeting")
    @SendTo("/topic/greetings")
    public MessageResponse greeting(MessageRequest message, Principal principal) throws Exception {
        if (message.getType() == MessageType.GREETING && message.getToken() != null
                && jwtComponent.getUsernameFromJwt(message.getToken()).equals(message.getSender())) {
            socketService.addUUID(message.getSender(), principal.getName());
        }
        return new MessageResponse(message.getSubject() + ", " + message.getSender() + "!", MessageType.GREETING);
    }

    @MessageMapping("/text")
    @SendTo("/topic/everyone")
    public MessageResponse sendChatMessageToEveryone(MessageRequest message) throws Exception {
        if (message.getToken() != null
                && jwtComponent.getUsernameFromJwt(message.getToken()).equals(message.getSender())) {
            return new MessageResponse(message.getSubject(), MessageType.CHAT, message.getBody(), message.getSender());
        }
        return null;
    }

    @MessageMapping("/chat")
    @SendToUser("/topic/user")
    public void sendChatMessage(@Payload MessageRequest message, @Header("simpSessionId") String sessionId)
            throws Exception {
        if (message.getToken() != null
                && jwtComponent.getUsernameFromJwt(message.getToken()).equals(message.getSender())) {
            MessageResponse response = new MessageResponse(message.getSubject(), MessageType.CHAT, message.getBody(),
                    message.getSender());
            simpMessagingTemplate.convertAndSendToUser(socketService.getUUIDFromUsername(message.getReceiver()),
                    "/topic/user", response);
        }
    }

    @MessageMapping("/challenge")
    @SendToUser("/topic/challenge")
    public void sendChallenge(@Payload MessageRequest message, @Header("simpSessionId") String sessionId)
            throws Exception {
        if (message.getToken() != null
                && jwtComponent.getUsernameFromJwt(message.getToken()).equals(message.getSender())) {
            MessageResponse response = new MessageResponse(message.getSubject(), MessageType.CHALLENGE,
                    message.getBody(),
                    message.getSender());
            simpMessagingTemplate.convertAndSendToUser(socketService.getUUIDFromUsername(message.getReceiver()),
                    "/topic/challenge", response);
        }
    }
}