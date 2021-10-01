package matej.tejkogames.interfaces.controllers;

import java.util.UUID;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.UserRequest;

public interface UserController extends ControllerInterface<User, UUID, UserRequest> {
    
}
