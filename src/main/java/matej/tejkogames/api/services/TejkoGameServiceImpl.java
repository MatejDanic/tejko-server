package matej.tejkogames.api.services;

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
	public List<TejkoGame> getAll(Integer page, Integer size, String sort, String direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
		return tejkoGameRepository.findAll(pageable).getContent();
	}
	
	@Override
	public List<TejkoGame> getAllByIdIn(Set<Integer> idSet) {
		return tejkoGameRepository.findAllById(idSet);
	}

    @Override
    public TejkoGame create(TejkoGameRequest requestBody) {
        return null;
    }

    @Override
	public TejkoGame updateById(Integer id, TejkoGameRequest requestBody) {
		TejkoGame tejkoGame = getById(id);

		tejkoGame.updateByRequest(requestBody);
		
		return tejkoGameRepository.save(tejkoGame);
	}

	@Override
	public List<TejkoGame> updateAll(Map<Integer, TejkoGameRequest> idRequestMap) {
		List<TejkoGame> tejkoGameList = getAllByIdIn(idRequestMap.keySet());

		for (TejkoGame tejkoGame : tejkoGameList) {
			tejkoGame.updateByRequest(idRequestMap.get(tejkoGame.getId()));
		}

		return tejkoGameRepository.saveAll(tejkoGameList);
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
