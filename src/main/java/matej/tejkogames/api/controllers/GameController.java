package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.GameService;
import matej.tejkogames.interfaces.api.controllers.GameControllerInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.payload.requests.GameRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/games")
public class GameController implements GameControllerInterface {

    @Autowired
    GameService gameService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Game> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<Game>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(gameService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Game> create(@RequestBody GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<Game>> createBulk(@RequestBody List<GameRequest> objectRequestList) {
        return new ResponseEntity<>(gameService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Game> updateById(@PathVariable Integer id, @RequestBody GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bulk")
    @Override
    public ResponseEntity<List<Game>> updateBulkById(@RequestBody Map<Integer, GameRequest> idObjectRequestMap) {
        return new ResponseEntity<>(gameService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            @PathVariable Integer id) {
        gameService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("Game", "Game has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        gameService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("Game", "All games have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
            @RequestBody Set<Integer> idSet) {
        gameService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Game", "All games have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/scores")
    @Override
    public ResponseEntity<List<Score>> getScoresByGameId(Integer id) {
        return new ResponseEntity<>(gameService.getScoresByGameId(id), HttpStatus.OK);
    }

}
