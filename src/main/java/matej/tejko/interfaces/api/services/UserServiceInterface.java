package matej.tejko.interfaces.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import matej.tejko.exceptions.RoleNotFoundException;
import matej.tejko.models.general.Preference;
import matej.tejko.models.general.Role;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.UserRequest;

public interface UserServiceInterface extends ServiceInterface<UUID, User, UserRequest> {

    public Preference getPreferenceByUserId(UUID id);

    public void deletePreferenceByUserId(UUID id);

    public Preference savePreferenceByUserId(UUID id);

    public Set<Role> assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException;

    public List<Score> getScoresByUserId(UUID id);

    public User getByUsername(String username);

}
