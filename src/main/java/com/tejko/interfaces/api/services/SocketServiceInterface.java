package com.tejko.interfaces.api.services;

import java.security.Principal;
import com.tejko.models.general.payload.requests.MessageRequest;
import com.tejko.models.general.payload.responses.ApiResponse;

public interface SocketServiceInterface {

    public ApiResponse<?> greet(MessageRequest messageRequest, Principal principal);

    public ApiResponse<?> sendMessage(MessageRequest messageRequest, String sessionId);

    public ApiResponse<?> challenge(MessageRequest messageRequest, String sessionId);

    public ApiResponse<?> accept(MessageRequest messageRequest, String sessionId);

}
