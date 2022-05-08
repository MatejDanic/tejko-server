package matej.tejkogames.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import matej.tejkogames.interfaces.api.services.ScoreServiceInterface;

@Service
public class ScoreService implements ScoreServiceInterface {

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
	public List<Score> getBulkById(Set<UUID> idSet) {
		return scoreRepository.findAllById(idSet);
	}

	@Override
	public Score create(ScoreRequest objectRequest) {
		Score score = scoreFactory.createScore(objectRequest.getUser(), objectRequest.getValue());
		return scoreRepository.save(score);
	}

	@Override
	public List<Score> createBulk(List<ScoreRequest> objectRequestList) {
		List<Score> scoreList = new ArrayList<>();

		for (ScoreRequest scoreRequest : objectRequestList) {
			Score score = new Score();
			score.updateByRequest(scoreRequest);
		}

		return scoreRepository.saveAll(scoreList);
	}

	@Override
	public Score updateById(UUID id, ScoreRequest objectRequest) {
		Score score = getById(id);

		score.updateByRequest(objectRequest);

		return scoreRepository.save(score);
	}

	@Override
	public List<Score> updateBulkById(Map<UUID, ScoreRequest> idObjectRequestMap) {
		List<Score> scoreList = getBulkById(idObjectRequestMap.keySet());

		for (Score score : scoreList) {
			score.updateByRequest(idObjectRequestMap.get(score.getId()));
		}

		return scoreRepository.saveAll(scoreList);
	}

	@Override
	public void deleteById(UUID id) {
		scoreRepository.deleteById(id);
	}

	@Override
	public void deleteBulkById(Set<UUID> idSet) {
		scoreRepository.deleteAllById(idSet);
	}

	@Override
	public void deleteAll() {
		scoreRepository.deleteAll();
	}

	@Override
	public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end) {
		return scoreRepository.findAllByDateBetween(start, end);
	}

}
