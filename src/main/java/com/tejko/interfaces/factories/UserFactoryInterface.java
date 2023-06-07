package com.tejko.interfaces.factories;

import com.tejko.interfaces.ObjectFactoryInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.UserRequest;

public interface UserFactoryInterface extends ObjectFactoryInterface<User, UserRequest> {

}
