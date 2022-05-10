package matej.tejko.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import matej.tejko.api.services.LogService;
import matej.tejko.interfaces.api.controllers.LogControllerInterface;
import matej.tejko.models.general.Log;
import matej.tejko.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/logs")
public class LogController implements LogControllerInterface {

	@Autowired
	LogService logService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Log> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(logService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<Log>> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "desc") String direction) {
		return new ResponseEntity<>(logService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
		logService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Log", "Exception Log has been deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		logService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Log", "All Exception Logs have been deleted."), HttpStatus.OK);
	}

}
