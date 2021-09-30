package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.UserRequest;

public interface UserService extends ServiceInterface<User, UUID, UserRequest> {

	public boolean hasPermission(UUID id, String username);

}
