package matej.tejkogames.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.api.repositories.TejkoGameRepository;
import matej.tejkogames.interfaces.services.TejkoGameService;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.TejkoGame;
import matej.tejkogames.models.general.payload.requests.TejkoGameRequest;

@Service
public class TejkoGameServiceImpl implements TejkoGameService {

    @Autowired
    TejkoGameRepository tejkoGameRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public TejkoGame getById(Integer id) {
        return tejkoGameRepository.getById(id);
    }

    @Override
    public List<TejkoGame> getAll() {
        return tejkoGameRepository.findAll();
    }

    @Override
    public TejkoGame create(TejkoGameRequest requestBody) {
        return null;
    }

    @Override
    public TejkoGame updateById(Integer id, TejkoGameRequest requestBody) {
        TejkoGame tejkoGame = getById(id);
        if (requestBody.getName() != null) {
            tejkoGame.setName(requestBody.getName());
        }
        if (requestBody.getDescription() != null) {
            tejkoGame.setDescription(requestBody.getDescription());
        }
        return tejkoGameRepository.save(tejkoGame);
    }

    @Override
    public void deleteById(Integer id) {
        tejkoGameRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tejkoGameRepository.deleteAll();
    }

    @Override
    public List<Score> getScoresByGameId(Integer id) {
        return scoreRepository.findAllByGameId(id);
    }

}
