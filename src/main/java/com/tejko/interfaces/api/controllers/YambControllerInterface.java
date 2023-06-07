package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.tejko.exceptions.IllegalActionException;
import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface YambControllerInterface extends ObjectControllerInterface<UUID, Yamb, YambRequest, YambResponse> {

        @PutMapping("/{id}/roll")
        public ResponseEntity<YambResponse> rollDiceById(@PathVariable UUID id, @RequestBody List<Integer> diceToRoll) throws IllegalActionException;

        @PutMapping("/{id}/announce")
        public ResponseEntity<YambResponse> announceById(@PathVariable UUID id, @RequestBody BoxType boxType) throws IllegalActionException;

        @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
        public ResponseEntity<YambResponse> fillById(UUID id, @PathVariable ColumnType columnType, @PathVariable BoxType boxType) throws IllegalActionException;

        @PutMapping("/{id}/restart")
        public ResponseEntity<YambResponse> restartById(UUID id) throws IllegalActionException;

}
