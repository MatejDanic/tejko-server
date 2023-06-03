package com.tejko.interfaces.api.services;

import java.util.List;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.models.general.App;

public interface AppServiceInterface extends ServiceInterface<Integer, App, AppRequest, AppResponse> {

    public List<Score> getScoresByAppId(Integer id);

}
