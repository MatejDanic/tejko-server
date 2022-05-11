package com.tejko.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.api.repositories.ScoreRepository;
import com.tejko.factories.ScoreFactory;
import com.tejko.interfaces.api.services.ScoreServiceInterface;

@Service
public class ScoreService implements ScoreServiceInterface {

	@Autowired
	ScoreFactory scoreFactory;

	@Autowired
	ScoreRepository scoreRepository;

	@Override
	public Score getById(UUID id) {
		return scoreRepository.findById(id).get();
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
		Score score = scoreFactory.create(objectRequest);
		return scoreRepository.save(score);
	}

	@Override
	public List<Score> createBulk(List<ScoreRequest> objectRequestList) {
		List<Score> scoreList = new ArrayList<>();

		for (ScoreRequest objectRequest : objectRequestList) {
			scoreList.add(scoreFactory.create(objectRequest));
		}

		return scoreRepository.saveAll(scoreList);
	}

	@Override
	public Score updateById(UUID id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
		Score score = getById(id);

		score = applyPatch(score, objectPatch);

		return scoreRepository.save(score);
	}

	@Override
	public List<Score> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
			throws JsonProcessingException, JsonPatchException {
		List<Score> scoreList = getBulkById(idObjectPatchMap.keySet());

		for (Score score : scoreList) {
			score = applyPatch(score, idObjectPatchMap.get(score.getId()));
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

	@Override
	public boolean hasPermission(UUID userId, UUID objectId) {
		return false;
	}

	@Override
	public Score applyPatch(Score object, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
		return objectMapper.treeToValue(patched, Score.class);
	}

}
