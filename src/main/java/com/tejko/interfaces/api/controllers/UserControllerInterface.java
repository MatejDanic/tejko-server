package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface UserControllerInterface extends ObjectControllerInterface<UUID, User, UserRequest, UserResponse> {

    @PutMapping("/{id}/assign-role")
    public ResponseEntity<UserResponse> assignRoleByUserId(@PathVariable UUID id, @RequestBody String label);

    @GetMapping("/{id}/preference")
    public ResponseEntity<PreferenceResponse> getPreferenceByUserId(UUID id);

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<ScoreResponse>> getScoresByUserId(UUID id);

}
