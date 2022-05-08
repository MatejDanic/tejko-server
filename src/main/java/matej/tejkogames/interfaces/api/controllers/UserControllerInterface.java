package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.UserRequest;

public interface UserControllerInterface extends ControllerInterface<User, UUID, UserRequest> {

}
