package matej.tejkogames.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import matej.tejkogames.models.general.Log;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface LogControllerInterface {

    @GetMapping("/{id}")
    public ResponseEntity<Log> getById(@PathVariable UUID id);

    @GetMapping("")
    public ResponseEntity<List<Log>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction);

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id);

    @DeleteMapping("")
    public ResponseEntity<MessageResponse> deleteAll();

}
