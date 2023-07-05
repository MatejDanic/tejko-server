package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.factories.GameFactory;
import com.tejko.interfaces.api.services.GameServiceInterface;
import com.tejko.mappers.GameMapper;
import com.tejko.api.repositories.GameRepository;
import com.tejko.models.general.Game;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.responses.GameResponse;

@Service
public class GameService implements GameServiceInterface {

    @Autowired
    GameRepository gameRepository;

    @Resource
    GameFactory gameFactory;

    @Resource
    GameMapper gameMapper;

    @Autowired
    ScoreService scoreService;

    @Override
    public GameResponse getById(UUID id) {
        return gameMapper.toRestResponse(gameRepository.getById(id));
    }

    @Override
    public List<GameResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return gameMapper.toRestResponseList(gameRepository.findAll(pageable).getContent());
    }

    @Override
    public List<GameResponse> getBulkById(Set<UUID> idSet) {
        return gameMapper.toRestResponseList(gameRepository.findAllById(idSet));
    }

    @Override
    public GameResponse create(GameRequest gameRequest) {
        Game game = gameFactory.getObject(gameRequest);
        return gameMapper.toRestResponse(gameRepository.save(game));
    }

    @Override
    public List<GameResponse> createBulk(List<GameRequest> objectRequestList) {
        List<Game> gameList = new ArrayList<>();

        for (GameRequest objectRequest : objectRequestList) {
            gameList.add(gameFactory.getObject(objectRequest));
        }

        return gameMapper.toRestResponseList(gameRepository.saveAll(gameList));
    }

    @Override
    public GameResponse updateById(UUID id, GameRequest gameRequest) {
        Game game = gameRepository.getById(id);

        game = applyPatch(game, gameRequest);

        return gameMapper.toRestResponse(gameRepository.save(game));
    }

    @Override
    public List<GameResponse> updateBulkById(Map<UUID, GameRequest> idGameRequestMap) {
        List<Game> gameList = gameRepository.findAllById(idGameRequestMap.keySet());

        for (Game game : gameList) {
            game = applyPatch(game, idGameRequestMap.get(game.getId()));
        }

        return gameMapper.toRestResponseList(gameRepository.saveAll(gameList));
    }

    @Override
    public void deleteById(UUID id) {
        gameRepository.deleteById(id);
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        gameRepository.deleteAllById(idSet);
    }

    @Override
    public void deleteAll() {
        gameRepository.deleteAll();
    }

    @Override
    public boolean hasPermission(UUID userId, UUID gameId) {
        return false;
    }

    @Override
    public Game applyPatch(Game object, GameRequest objectRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPatch'");
    }

}
