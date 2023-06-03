package com.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tejko.models.general.payload.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ControllerInterface<I, T, R, A extends ApiResponse<T>> {

        @GetMapping("/{id}")
        public ResponseEntity<A> getById(@PathVariable I id);

        @GetMapping("/bulk")
        public ResponseEntity<List<A>> getBulkById(@RequestBody Set<I> idSet);

        @GetMapping("")
        public ResponseEntity<List<A>> getAll(@RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
                        @RequestParam(defaultValue = "desc") String direction);

        @PostMapping("")
        public ResponseEntity<A> create(@RequestBody R objectPatch);

        @PostMapping("/bulk")
        public ResponseEntity<List<A>> createBulk(@RequestBody List<R> objectRequestList);

        @PatchMapping("/{id}")
        public ResponseEntity<A> updateById(@PathVariable I id, @RequestBody R objectRequest);

        @PatchMapping("/bulk")
        public ResponseEntity<List<A>> updateBulkById(@RequestBody Map<I, R> idObjectRequestMap);

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteById(@PathVariable I id);

        @DeleteMapping("/bulk")
        public ResponseEntity<?> deleteBulkById(@RequestBody Set<I> idSet);

        @DeleteMapping("")
        public ResponseEntity<?> deleteAll();

}
