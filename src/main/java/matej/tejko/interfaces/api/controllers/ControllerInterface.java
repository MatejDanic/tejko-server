package matej.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejko.models.general.payload.responses.MessageResponse;

public interface ControllerInterface<I, T, R> {

        @GetMapping("/{id}")
        public ResponseEntity<T> getById(@PathVariable I id);

        @GetMapping("/bulk")
        public ResponseEntity<List<T>> getBulkById(@RequestBody Set<I> idSet);

        @GetMapping("")
        public ResponseEntity<List<T>> getAll(@RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
                        @RequestParam(defaultValue = "desc") String direction);

        @PostMapping("")
        public ResponseEntity<T> create(@RequestBody R objectPatch);

        @PostMapping("/bulk")
        public ResponseEntity<List<T>> createBulk(@RequestBody List<R> objectPatchList);

        @PutMapping("/{id}")
        public ResponseEntity<T> updateById(@PathVariable I id, @RequestBody JsonPatch objectRequest)
                        throws JsonProcessingException, JsonPatchException;

        @PutMapping("/bulk")
        public ResponseEntity<List<T>> updateBulkById(@RequestBody Map<I, JsonPatch> idObjectRequestMap)
                        throws JsonProcessingException, JsonPatchException;

        @DeleteMapping("/{id}")
        public ResponseEntity<MessageResponse> deleteById(@PathVariable I id);

        @DeleteMapping("/bulk")
        public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<I> idSet);

        @DeleteMapping("")
        public ResponseEntity<MessageResponse> deleteAll();

}
