package com.tejko.interfaces.api.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tejko.interfaces.api.RestController;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;

public interface ScoreControllerInterface extends RestController<UUID, Score, ScoreRequest, ScoreResponse> {

    @GetMapping("/interval")
    public ResponseEntity<List<ScoreResponse>> getAllByDateInterval(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate);

}
