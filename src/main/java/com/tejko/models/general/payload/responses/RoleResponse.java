package com.tejko.models.general.payload.responses;

import com.tejko.models.general.Role;
import com.tejko.models.general.enums.ResponseStatus;

public class RoleResponse extends ApiResponse<Role> {

    public RoleResponse(ResponseStatus status, String message, Role object) {
        super(status, message, object);
    }

    public RoleResponse(Role object) {
        super(object);
    }

}