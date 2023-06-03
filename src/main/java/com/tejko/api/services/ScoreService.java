package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.DateIntervalRequest;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.api.repositories.ScoreRepository;
import com.tejko.factories.ScoreFactory;
import com.tejko.interfaces.api.services.ScoreServiceInterface;

@Service
public class ScoreService implements ScoreServiceInterface {

	@Resource
	ScoreFactory scoreFactory;

	@Autowired
	ScoreRepository scoreRepository;

	@Override
	public ScoreResponse getById(UUID id) {
		return toApiResponse(scoreRepository.getById(id));
	}

	@Override
	public List<ScoreResponse> getAll(Integer page, Integer size, String sort, String direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
		return toApiResponseList(scoreRepository.findAll(pageable).getContent());
	}

	@Override
	public List<ScoreResponse> getBulkById(Set<UUID> idSet) {
		return toApiResponseList(scoreRepository.findAllById(idSet));
	}

	@Override
	public ScoreResponse create(ScoreRequest objectRequest) {
		Score score = scoreFactory.getObject(objectRequest);
		return toApiResponse(scoreRepository.save(score));
	}

	@Override
	public List<ScoreResponse> createBulk(List<ScoreRequest> objectRequestList) {
		List<Score> scoreList = new ArrayList<>();

		for (ScoreRequest objectRequest : objectRequestList) {
			scoreList.add(scoreFactory.getObject(objectRequest));
		}

		return toApiResponseList(scoreRepository.saveAll(scoreList));
	}

	@Override
	public ScoreResponse updateById(UUID id, ScoreRequest scoreRequest) {
		Score score = scoreRepository.getById(id);

		score = applyPatch(score, scoreRequest);

		return toApiResponse(scoreRepository.save(score));
	}

	@Override
	public List<ScoreResponse> updateBulkById(Map<UUID, ScoreRequest> idScoreRequestMap) {
		List<Score> scoreList = scoreRepository.findAllById(idScoreRequestMap.keySet());

		for (Score score : scoreList) {
			score = applyPatch(score, idScoreRequestMap.get(score.getId()));
		}

		return toApiResponseList(scoreRepository.saveAll(scoreList));
	}

	@Override
	public ApiResponse<?> deleteById(UUID id) {
		scoreRepository.deleteById(id);
		return new ApiResponse<>("Score has been deleted successfully.");
	}

	@Override
	public ApiResponse<?> deleteBulkById(Set<UUID> idSet) {
		scoreRepository.deleteAllById(idSet);
		return new ApiResponse<>("Scores have been deleted successfully.");
	}

	@Override
	public ApiResponse<?> deleteAll() {
		scoreRepository.deleteAll();
		return new ApiResponse<>("All Scores have been deleted successfully.");
	}

	@Override
	public List<ScoreResponse> getAllByDateInterval(DateIntervalRequest dateIntervalRequest) {
		return toApiResponseList(scoreRepository.findAllByDateBetween(dateIntervalRequest.getStartDate(), dateIntervalRequest.getEndDate()));
	}
	
	@Override
    public List<ScoreResponse> getScoresByUserId(UUID userId) {
        return toApiResponseList(scoreRepository.findAllByUserId(userId));
    }

	@Override
	public boolean hasPermission(UUID userId, UUID objectId) {
		return false;
	}

	@Override
	public Score applyPatch(Score score, ScoreRequest scoreRequest) {
		return score;
	}

	@Override
	public ScoreResponse toApiResponse(Score object) {
		return new ScoreResponse(object);
	}

	@Override
	public List<ScoreResponse> toApiResponseList(List<Score> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
	}

}
