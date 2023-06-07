package com.tejko.interfaces.api.services;

import com.tejko.models.general.payload.requests.LoginRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.LoginResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface AuthServiceInterface {
    
    public LoginResponse login(LoginRequest loginRequest);

    public UserResponse register(UserRequest userRequest);
    
}
