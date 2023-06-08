package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.UUID;

import com.tejko.exceptions.RoleNotFoundException;
import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface UserServiceInterface extends ObjectServiceInterface<UUID, User, UserRequest, UserResponse> {

    public PreferenceResponse getPreferenceByUserId(UUID id);

    public UserResponse assignRoleByUserId(UUID id, UUID roleId) throws RoleNotFoundException;

    public List<ScoreResponse> getScoresByUserId(UUID id);
    
    public List<UserResponse> getUsersByRoleId(UUID roleId);

    public UserResponse getByUsername(String username);

}
