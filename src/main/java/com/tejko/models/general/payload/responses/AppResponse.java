package com.tejko.models.general.payload.responses;

import com.tejko.models.general.App;
import com.tejko.models.general.enums.ResponseStatus;

public class AppResponse extends ApiResponse<App> {

    public AppResponse(ResponseStatus status, String message, App object) {
        super(status, message, object);
    }

    public AppResponse(App object) {
        super(object);
    }

}