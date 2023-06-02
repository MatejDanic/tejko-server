package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.tejko.exceptions.IllegalActionException;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface YambControllerInterface extends ControllerInterface<UUID, Yamb, YambRequest> {

        @PutMapping("/{id}/roll")
        public ResponseEntity<Yamb> rollDiceById(@PathVariable UUID id, @RequestBody List<Integer> diceToRoll) throws IllegalActionException;

        @PutMapping("/{id}/announce")
        public ResponseEntity<Yamb> announceById(@PathVariable UUID id, @RequestBody BoxType boxType) throws IllegalActionException;

        @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
        public ResponseEntity<Yamb> fillById(UUID id, @PathVariable ColumnType columnType, @PathVariable BoxType boxType) throws IllegalActionException;

        @PutMapping("/{id}/restart")
        public ResponseEntity<Yamb> restartById(UUID id) throws IllegalActionException;

}
