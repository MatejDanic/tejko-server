package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Override
    public ResponseEntity<Game> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> getBulkById(@RequestBody Set<Integer> idSet) {
        return new ResponseEntity<>(gameService.getBulkById(idSet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Game>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(gameService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Game> create(@RequestBody GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> createBulk(@RequestBody List<GameRequest> objectRequestList) {
        return new ResponseEntity<>(gameService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Game> updateById(@PathVariable Integer id, @RequestBody GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> updateBulkById(@RequestBody Map<Integer, GameRequest> idObjectRequestMap) {
        return new ResponseEntity<>(gameService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        gameService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("Game", "Game has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<Integer> idSet) {
        gameService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Game", "All games have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        gameService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("Game", "All games have been successfully deleted"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Score>> getScoresByGameId(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getScoresByGameId(id), HttpStatus.OK);
    }

}
