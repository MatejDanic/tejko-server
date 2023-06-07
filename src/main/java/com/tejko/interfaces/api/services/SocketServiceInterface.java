package com.tejko.interfaces.api.services;

import java.security.Principal;
import com.tejko.models.general.payload.requests.MessageRequest;

public interface SocketServiceInterface {

    public void greet(MessageRequest messageRequest, Principal principal);

    public void sendMessage(MessageRequest messageRequest, String sessionId);

    public void challenge(MessageRequest messageRequest, String sessionId);

    public void accept(MessageRequest messageRequest, String sessionId);

}
