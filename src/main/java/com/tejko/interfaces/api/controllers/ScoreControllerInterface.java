package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.DateIntervalRequest;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;

public interface ScoreControllerInterface extends ControllerInterface<UUID, Score, ScoreRequest, ScoreResponse> {

    @GetMapping("/interval")
    public ResponseEntity<List<ScoreResponse>> getAllByDateInterval(@RequestBody DateIntervalRequest dateIntervalRequest);

}
