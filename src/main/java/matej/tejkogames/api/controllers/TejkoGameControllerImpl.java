package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejkogames.api.services.TejkoGameServiceImpl;
import matej.tejkogames.interfaces.controllers.TejkoGameController;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.TejkoGame;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.TejkoGameRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/games")
public class TejkoGameControllerImpl implements TejkoGameController {

    @Autowired
    TejkoGameServiceImpl tejkoGameService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TejkoGame> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(tejkoGameService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
	public ResponseEntity<List<TejkoGame>> getAll(
						@RequestParam(defaultValue = "0") Integer page, 
						@RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "id") String sort,
                        @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(tejkoGameService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    @Override
    public ResponseEntity<TejkoGame> create(@RequestBody TejkoGameRequest requestBody) {
        return new ResponseEntity<>(tejkoGameService.create(requestBody), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TejkoGame> updateById(@PathVariable Integer id,
			@RequestBody TejkoGameRequest requestBody) {
		return new ResponseEntity<>(tejkoGameService.updateById(id, requestBody), HttpStatus.OK);
	}
	
	
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("")
    @Override
	public ResponseEntity<List<TejkoGame>> updateAll(@RequestBody Map<Integer, TejkoGameRequest> idRequestMap) {
		return new ResponseEntity<>(tejkoGameService.updateAll(idRequestMap), HttpStatus.OK);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            @PathVariable Integer id) {
        tejkoGameService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        tejkoGameService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/scores")
    @Override
    public ResponseEntity<List<Score>> getScoresByGameId(Integer id) {
        return new ResponseEntity<>(tejkoGameService.getScoresByGameId(id), HttpStatus.OK);
    }

}
