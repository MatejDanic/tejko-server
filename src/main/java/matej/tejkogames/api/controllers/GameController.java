package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Game> getById(Integer id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> getBulkById(Set<Integer> idSet) {
        return new ResponseEntity<>(gameService.getBulkById(idSet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Game>> getAll(Integer page, Integer size, String sort, String direction) {
        return new ResponseEntity<>(gameService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Game> create(GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> createBulk(List<GameRequest> objectRequestList) {
        return new ResponseEntity<>(gameService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Game> updateById(Integer id, GameRequest objectRequest) {
        return new ResponseEntity<>(gameService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Game>> updateBulkById(Map<Integer, GameRequest> idObjectRequestMap) {
        return new ResponseEntity<>(gameService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(Integer id) {
        gameService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("Game", "Game has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(Set<Integer> idSet) {
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
    public ResponseEntity<List<Score>> getScoresByGameId(Integer id) {
        return new ResponseEntity<>(gameService.getScoresByGameId(id), HttpStatus.OK);
    }

}
