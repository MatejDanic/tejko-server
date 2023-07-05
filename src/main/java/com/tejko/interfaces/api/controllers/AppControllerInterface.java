package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.interfaces.api.RestController;
import com.tejko.models.general.App;

public interface AppControllerInterface extends RestController<UUID, App, AppRequest, AppResponse> {

}
