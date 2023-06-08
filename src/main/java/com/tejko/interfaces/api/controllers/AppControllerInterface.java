package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.App;

public interface AppControllerInterface extends ObjectControllerInterface<UUID, App, AppRequest, AppResponse> {

}
