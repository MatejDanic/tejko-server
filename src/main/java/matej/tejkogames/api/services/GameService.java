package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.api.repositories.GameRepository;
import matej.tejkogames.interfaces.api.services.GameServiceInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.payload.requests.GameRequest;

@Service
public class GameService implements GameServiceInterface {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public Game getById(Integer id) {
        return gameRepository.getById(id);
    }

    @Override
    public List<Game> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return gameRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Game> getBulkById(Set<Integer> idSet) {
        return gameRepository.findAllById(idSet);
    }

    @Override
    public Game create(GameRequest objectRequest) {
        return null;
    }

    @Override
    public List<Game> createBulk(List<GameRequest> objectRequestList) {
        List<Game> gameList = new ArrayList<>();

        for (GameRequest gameRequest : objectRequestList) {
            Game game = new Game();
            game.updateByRequest(gameRequest);
        }

        return gameRepository.saveAll(gameList);
    }

    @Override
    public Game updateById(Integer id, GameRequest objectRequest) {
        Game game = getById(id);

        game.updateByRequest(objectRequest);

        return gameRepository.save(game);
    }

    @Override
    public List<Game> updateBulkById(Map<Integer, GameRequest> idObjectRequestMap) {
        List<Game> gameList = getBulkById(idObjectRequestMap.keySet());

        for (Game game : gameList) {
            game.updateByRequest(idObjectRequestMap.get(game.getId()));
        }

        return gameRepository.saveAll(gameList);
    }

    @Override
    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        gameRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<Integer> idSet) {
        gameRepository.deleteAllById(idSet);
    }

    @Override
    public List<Score> getScoresByGameId(Integer id) {
        return scoreRepository.findAllByGameId(id);
    }

}
