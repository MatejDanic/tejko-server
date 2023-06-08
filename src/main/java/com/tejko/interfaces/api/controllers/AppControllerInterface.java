package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.App;

public interface AppControllerInterface extends ObjectControllerInterface<UUID, App, AppRequest, AppResponse> {

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<ScoreResponse>> getScoresByAppId(@PathVariable UUID id);

}
