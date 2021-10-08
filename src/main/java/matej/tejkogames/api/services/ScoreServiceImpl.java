package matej.tejkogames.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;
import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.factories.ScoreFactory;
import matej.tejkogames.interfaces.services.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreRepository scoreRepository;

	@Autowired
	ScoreFactory scoreFactory;

	@Override
	public Score getById(UUID id) {
		return scoreRepository.getById(id);
	}

	@Override
	public List<Score> getAll(Integer page, Integer size, String sort, String direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
		return scoreRepository.findAll(pageable).getContent();
	}

	@Override
	public List<Score> getAllByIdIn(Set<UUID> idSet) {
		return scoreRepository.findAllById(idSet);
	}

	@Override
	public Score create(ScoreRequest requestBody) {
		Score score = scoreFactory.createScore(requestBody.getUser(), requestBody.getValue());
		return scoreRepository.save(score);
	}

	@Override
	public Score updateById(UUID id, ScoreRequest requestBody) {
		Score score = getById(id);

		score.updateByRequest(requestBody);

		return scoreRepository.save(score);
	}

	@Override
	public List<Score> updateAll(Map<UUID, ScoreRequest> idRequestMap) {
		List<Score> scoreList = getAllByIdIn(idRequestMap.keySet());

		for (Score score : scoreList) {
			score.updateByRequest(idRequestMap.get(score.getId()));
		}

		return scoreRepository.saveAll(scoreList);
	}

	@Override
	public void deleteById(UUID id) {
		scoreRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		scoreRepository.deleteAll();
	}

	@Override
	public void deleteAllById(Set<UUID> idSet) {
		scoreRepository.deleteAllById(idSet);
	}

	public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end) {
		return scoreRepository.findAllByDateBetween(start, end);
	}

}
