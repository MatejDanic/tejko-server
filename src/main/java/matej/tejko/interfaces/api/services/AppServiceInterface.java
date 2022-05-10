package matej.tejko.interfaces.api.services;

import java.util.List;

import matej.tejko.models.general.Score;
import matej.tejko.models.general.payload.requests.AppRequest;
import matej.tejko.models.general.App;

public interface AppServiceInterface extends ServiceInterface<Integer, App, AppRequest> {

    public List<Score> getScoresByAppId(Integer id);

}
