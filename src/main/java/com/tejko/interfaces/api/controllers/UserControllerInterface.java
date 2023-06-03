package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface UserControllerInterface extends ControllerInterface<UUID, User, UserRequest, UserResponse> {

    @PutMapping("/{id}/assign-role")
    public ResponseEntity<UserResponse> assignRoleByUserId(@PathVariable UUID id, @RequestBody Integer roleId);

    @GetMapping("/{id}/preference")
    public ResponseEntity<PreferenceResponse> getPreferenceByUserId(UUID id);

    @PatchMapping("/{id}/preference")
    public ResponseEntity<PreferenceResponse> updatePreferenceByUserId(UUID id, PreferenceRequest preferenceRequest);

    @DeleteMapping("/{id}/preference")
    public ResponseEntity<ApiResponse<?>> deletePreferenceByUserId(UUID id);

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<ScoreResponse>> getScoresByUserId(UUID id);

}
