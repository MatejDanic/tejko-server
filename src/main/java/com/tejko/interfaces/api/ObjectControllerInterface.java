package com.tejko.interfaces.api;

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

import com.tejko.models.general.payload.requests.ApiRequest;
import com.tejko.models.general.payload.responses.ApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ObjectControllerInterface<K, T, S extends ApiRequest<?>, U extends ApiResponse<?>> {

	@GetMapping("/{id}")
	public ResponseEntity<U> getById(@PathVariable K id);

	@GetMapping("/bulk")
	public ResponseEntity<List<U>> getBulkById(@RequestBody Set<K> idSet);

	@GetMapping("")
	public ResponseEntity<List<U>> getAll(
		@RequestParam(defaultValue = "0") Integer page, 
		@RequestParam(defaultValue = "10") Integer size, 
		@RequestParam(defaultValue = "id") String sort, 
		@RequestParam(defaultValue = "desc") String direction
	);

	@PostMapping("")
	public ResponseEntity<U> create(@RequestBody S objectPatch);

	@PostMapping("/bulk")
	public ResponseEntity<List<U>> createBulk(@RequestBody List<S> objectRequestList);

	@PatchMapping("/{id}")
	public ResponseEntity<U> updateById(@PathVariable K id, @RequestBody S objectRequest);

	@PatchMapping("/bulk")
	public ResponseEntity<List<U>> updateBulkById(@RequestBody Map<K, S> idObjectRequestMap);

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable K id);

	@DeleteMapping("/bulk")
	public ResponseEntity<?> deleteBulkById(@RequestBody Set<K> idSet);

	@DeleteMapping("")
	public ResponseEntity<?> deleteAll();

}
