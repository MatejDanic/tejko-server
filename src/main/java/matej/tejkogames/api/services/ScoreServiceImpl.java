package matej.tejkogames.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;
import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.interfaces.services.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public Score getById(UUID id) {
        return scoreRepository.getById(id);
    }

    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score updateById(UUID id, ScoreRequest requestBody) {
        Score score = getById(id);
        if (requestBody.getUser() != null) {
            score.setUser(requestBody.getUser());
        }
        if (requestBody.getDate() != null) {
            score.setDate(requestBody.getDate());
        }
        if (requestBody.getValue() != null) {
            score.setValue(requestBody.getValue());
        }
        return scoreRepository.save(score);
    }

    @Override
    public Score create(ScoreRequest requestBody) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        scoreRepository.deleteAll();
    }

    public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end) {
        return scoreRepository.findAllByDateBetween(start, end);
    }

}
