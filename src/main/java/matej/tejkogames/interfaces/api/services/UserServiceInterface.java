package matej.tejkogames.interfaces.api.services;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.UserRequest;

public interface UserServiceInterface extends ServiceInterface<User, UUID, UserRequest> {

}
