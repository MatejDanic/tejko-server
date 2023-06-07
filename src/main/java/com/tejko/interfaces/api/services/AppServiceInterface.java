package com.tejko.interfaces.api.services;

import java.util.List;

import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.App;

public interface AppServiceInterface extends ObjectServiceInterface<Integer, App, AppRequest, AppResponse> {

    public List<ScoreResponse> getScoresByAppId(Integer id);

}
