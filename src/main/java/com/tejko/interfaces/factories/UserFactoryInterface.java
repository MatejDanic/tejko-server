package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.UserRequest;

public interface UserFactoryInterface extends DatabaseEntityFactory<User, UserRequest> {

}
