package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejko.models.general.payload.responses.LogResponse;

public interface LogControllerInterface {

    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getById(@PathVariable UUID id);

    @GetMapping("")
    public ResponseEntity<List<LogResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction);

    @GetMapping("")
    public ResponseEntity<List<LogResponse>> getBulkById(@RequestBody Set<UUID> idSet);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id);

    @DeleteMapping("")
    public ResponseEntity<?> deleteAll();

}
