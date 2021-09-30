package matej.tejkogames.interfaces.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.ApiError;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

public interface ApiErrorController {

    public ResponseEntity<ApiError> getById(UUID id);

    public ResponseEntity<List<ApiError>> getAll(Integer page, Integer size, String sort, String direction);
	
	public ResponseEntity<MessageResponse> deleteById(String headerAuth, UUID id);

	public ResponseEntity<MessageResponse> deleteAll(String headerAuth);

}
