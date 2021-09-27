package matej.tejkogames.interfaces.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface ControllerInterface<T, I, R> {

    public ResponseEntity<T> getById(I id);

    public ResponseEntity<List<T>> getAll();

    public ResponseEntity<T> create(R requestBody);
    
    public ResponseEntity<T> updateById(I id, R requestBody);

    public ResponseEntity<MessageResponse> deleteById(String headerAuth, I id);

    public ResponseEntity<MessageResponse> deleteAll(String headerAuth);

}
