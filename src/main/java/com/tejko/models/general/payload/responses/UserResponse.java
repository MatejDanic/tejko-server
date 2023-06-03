package com.tejko.models.general.payload.responses;

import com.tejko.models.general.User;
import com.tejko.models.general.enums.ResponseStatus;

public class UserResponse extends ApiResponse<User> {

    public UserResponse(ResponseStatus status, String message, User object) {
        super(status, message, object);
    }

    public UserResponse(User object) {
        super(object);
    }

}