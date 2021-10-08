package matej.tejkogames.interfaces.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface ControllerInterface<T, I, R> {

    public ResponseEntity<T> create(R requestBody);

    public ResponseEntity<T> getById(I id);

    public ResponseEntity<List<T>> getAll(Integer page, Integer size, String sort, String direction);

    public ResponseEntity<T> updateById(I id, R requestBody);

    public ResponseEntity<List<T>> updateAll(Map<I, R> idRequestMap);

    public ResponseEntity<MessageResponse> deleteById(String headerAuth, I id);

    public ResponseEntity<MessageResponse> deleteAll(String headerAuth);

    public ResponseEntity<MessageResponse> deleteAllById(String headerAuth, Set<I> idSet);

}
