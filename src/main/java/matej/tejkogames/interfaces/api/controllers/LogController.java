package matej.tejkogames.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.Log;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface LogController {

    public ResponseEntity<Log> getById(UUID id);

    public ResponseEntity<List<Log>> getAll(Integer page, Integer size, String sort, String direction);

    public ResponseEntity<MessageResponse> deleteById(String headerAuth, UUID id);

    public ResponseEntity<MessageResponse> deleteAll(String headerAuth);

}
