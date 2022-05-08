package matej.tejkogames.interfaces.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import matej.tejkogames.exceptions.RoleNotFoundException;
import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.UserRequest;
import matej.tejkogames.models.yamb.enums.YambType;

public interface UserServiceInterface extends ServiceInterface<User, UUID, UserRequest> {

    public Set<Yamb> getYambsByUserId(UUID id);

    public List<Yamb> getYambsByTypeAndUserId(UUID id, YambType type);

    public Preference getPreferenceByUserId(UUID id);

    public void deletePreferenceByUserId(UUID id);

    public Preference savePreferenceByUserId(UUID id);

    public Set<Role> assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException;

    public List<Score> getScoresByUserId(UUID id);

    public User getByUsername(String username);
}
