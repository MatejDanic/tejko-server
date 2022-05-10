package matej.tejko.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import matej.tejko.models.general.Score;
import matej.tejko.models.general.payload.requests.AppRequest;
import matej.tejko.models.general.App;

public interface AppControllerInterface extends ControllerInterface<Integer, App, AppRequest> {

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<Score>> getScoresByAppId(@PathVariable Integer id);

}
