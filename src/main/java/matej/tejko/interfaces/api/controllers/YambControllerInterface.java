package matej.tejko.interfaces.api.controllers;

import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import matej.tejko.exceptions.IllegalActionException;
import matej.tejko.models.general.payload.requests.YambRequest;
import matej.tejko.models.yamb.Dice;
import matej.tejko.models.yamb.Yamb;
import matej.tejko.models.yamb.enums.BoxType;
import matej.tejko.models.yamb.enums.ColumnType;

public interface YambControllerInterface extends ControllerInterface<UUID, Yamb, YambRequest> {

        @PutMapping("/{id}/roll")
        public ResponseEntity<Set<Dice>> rollDiceById(@PathVariable UUID id) throws IllegalActionException;

        @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/announce")
        public ResponseEntity<BoxType> announceById(@PathVariable UUID id, @PathVariable ColumnType columnType,
                        @PathVariable BoxType boxType) throws IllegalActionException;

        @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
        public ResponseEntity<Yamb> fillById(UUID id, @PathVariable ColumnType columnType,
                        @PathVariable BoxType boxType) throws IllegalActionException;

}
