package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tejko.exceptions.RoleNotFoundException;
import com.tejko.models.general.Preference;
import com.tejko.models.general.Role;
import com.tejko.models.general.Score;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.requests.UserRequest;

public interface UserServiceInterface extends ServiceInterface<UUID, User, UserRequest> {

    public Preference getPreferenceByUserId(UUID id);

    public void deletePreferenceByUserId(UUID id);

    public Preference savePreferenceByUserId(UUID id, PreferenceRequest preferenceRequest);

    public Set<Role> assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException;

    public List<Score> getScoresByUserId(UUID id);

    public User getByUsername(String username);

}
