package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.responses.UserResponse;

public interface UserMapperInterface extends DatabaseEntityMapper<User, UserResponse> {
    
}
