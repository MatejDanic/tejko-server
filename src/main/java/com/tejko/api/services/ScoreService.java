package com.tejko.api.services;

import java.time.LocalDateTime;
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

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.api.repositories.ScoreRepository;
import com.tejko.factories.ScoreFactory;
import com.tejko.interfaces.api.services.ScoreServiceInterface;
import com.tejko.mappers.ScoreMapper;

@Service
public class ScoreService implements ScoreServiceInterface {


	@Autowired
	ScoreRepository scoreRepository;
	
	@Resource
	ScoreFactory scoreFactory;
	
	@Resource
	ScoreMapper scoreMapper;

	@Override
	public ScoreResponse getById(UUID id) {
		return scoreMapper.toApiResponse(scoreRepository.getById(id));
	}

	@Override
	public List<ScoreResponse> getAll(Integer page, Integer size, String sort, String direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
		return scoreMapper.toApiResponseList(scoreRepository.findAll(pageable).getContent());
	}

	@Override
	public List<ScoreResponse> getBulkById(Set<UUID> idSet) {
		return scoreMapper.toApiResponseList(scoreRepository.findAllById(idSet));
	}

	@Override
	public ScoreResponse create(ScoreRequest objectRequest) {
		Score score = scoreFactory.getObject(objectRequest);
		return scoreMapper.toApiResponse(scoreRepository.save(score));
	}

	@Override
	public List<ScoreResponse> createBulk(List<ScoreRequest> objectRequestList) {
		List<Score> scoreList = new ArrayList<>();

		for (ScoreRequest objectRequest : objectRequestList) {
			scoreList.add(scoreFactory.getObject(objectRequest));
		}

		return scoreMapper.toApiResponseList(scoreRepository.saveAll(scoreList));
	}

	@Override
	public ScoreResponse updateById(UUID id, ScoreRequest scoreRequest) {
		Score score = scoreRepository.getById(id);

		score = applyPatch(score, scoreRequest);

		return scoreMapper.toApiResponse(scoreRepository.save(score));
	}

	@Override
	public List<ScoreResponse> updateBulkById(Map<UUID, ScoreRequest> idScoreRequestMap) {
		List<Score> scoreList = scoreRepository.findAllById(idScoreRequestMap.keySet());

		for (Score score : scoreList) {
			score = applyPatch(score, idScoreRequestMap.get(score.getId()));
		}

		return scoreMapper.toApiResponseList(scoreRepository.saveAll(scoreList));
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
	public List<ScoreResponse> getAllByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
		return scoreMapper.toApiResponseList(scoreRepository.findAllByDateBetween(startDate, endDate));
	}
	
	@Override
    public List<ScoreResponse> getScoresByUserId(UUID userId) {
        return scoreMapper.toApiResponseList(scoreRepository.findAllByUserId(userId));
    }

	@Override
    public List<ScoreResponse> getScoresByAppId(Integer appId) {
        return scoreMapper.toApiResponseList(scoreRepository.findAllByAppId(appId));
    }

	@Override
	public boolean hasPermission(UUID userId, UUID objectId) {
		return false;
	}

	@Override
	public Score applyPatch(Score score, ScoreRequest scoreRequest) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toApiResponseList'");
	}

}
