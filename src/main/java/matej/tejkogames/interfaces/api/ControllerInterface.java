package matej.tejkogames.interfaces.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface ControllerInterface<T, I, R> {

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable I id);

    @GetMapping("/bulk")
    public ResponseEntity<List<T>> getBulkById(@RequestBody Set<I> idSet);

    @GetMapping("")
    public ResponseEntity<List<T>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction);

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody R objectRequest);

    @PostMapping("/bulk")
    public ResponseEntity<List<T>> createBulk(@RequestBody List<R> objectRequestList);

    @PatchMapping("/{id}")
    public ResponseEntity<T> updateById(@PathVariable I id, @RequestBody R objectRequest);

    @PatchMapping("/bulk")
    public ResponseEntity<List<T>> updateBulkById(@RequestBody Map<I, R> idObjectRequestMap);

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable I id);

    @DeleteMapping("/bulk")
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<I> idSet);

    @DeleteMapping("")
    public ResponseEntity<MessageResponse> deleteAll();

}
