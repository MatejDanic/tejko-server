package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejko.models.general.Log;
import com.tejko.models.general.payload.responses.MessageResponse;

public interface LogControllerInterface {

    @GetMapping("/{id}")
    public ResponseEntity<Log> getById(@PathVariable UUID id);

    @GetMapping("")
    public ResponseEntity<List<Log>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction);

    @GetMapping("")
    public ResponseEntity<List<Log>> getBulkById(@RequestBody Set<UUID> idSet);

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id);

    @DeleteMapping("")
    public ResponseEntity<MessageResponse> deleteAll();

}
