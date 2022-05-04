package matej.tejkogames.interfaces.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface ControllerInterface<T, I, R> {

    public ResponseEntity<T> create(R objectRequest);

    public ResponseEntity<List<T>> createBulk(List<R> objectRequestList);

    public ResponseEntity<T> getById(I id);

    public ResponseEntity<List<T>> getAll(Integer page, Integer size, String sort, String direction);

    public ResponseEntity<T> updateById(I id, R objectRequest);

    public ResponseEntity<List<T>> updateBulkById(Map<I, R> idObjectRequestMap);

    public ResponseEntity<MessageResponse> deleteById(String headerAuth, I id);

    public ResponseEntity<MessageResponse> deleteAll(String headerAuth);

    public ResponseEntity<MessageResponse> deleteBulkById(String headerAuth, Set<I> idSet);

}
