package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.interfaces.api.RestService;
import com.tejko.models.general.App;

public interface AppServiceInterface extends RestService<UUID, App, AppRequest, AppResponse> {

    //public List<ScoreResponse> getScoresByAppId(UUID id);

}
