package matej.tejkogames.interfaces.api.controllers;

import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import matej.tejkogames.exceptions.IllegalMoveException;
import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.ColumnType;

public interface YambControllerInterface extends ControllerInterface<Yamb, UUID, YambRequest> {

    @PutMapping("/{id}/roll")
    public ResponseEntity<Set<Dice>> rollDiceById(@PathVariable UUID id) throws IllegalMoveException;

    @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/announce")
    public ResponseEntity<BoxType> announceById(@PathVariable UUID id, @PathVariable ColumnType columnType,
            @PathVariable BoxType boxType)
            throws IllegalMoveException;

    @PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
    public ResponseEntity<Yamb> fillById(UUID id, @PathVariable ColumnType columnType, @PathVariable BoxType boxType)
            throws IllegalMoveException;

}
