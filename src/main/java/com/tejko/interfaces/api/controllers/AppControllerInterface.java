package com.tejko.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.models.general.App;

public interface AppControllerInterface extends ControllerInterface<Integer, App, AppRequest, AppResponse> {

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<Score>> getScoresByAppId(@PathVariable Integer id);

}
