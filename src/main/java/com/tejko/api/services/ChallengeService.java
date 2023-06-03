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

import com.tejko.api.repositories.ChallengeRepository;
import com.tejko.factories.ChallengeFactory;
import com.tejko.interfaces.api.services.ChallengeServiceInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.ChallengeResponse;

@Service
public class ChallengeService implements ChallengeServiceInterface {

    @Autowired
    ChallengeRepository challengeRepository;

    @Resource
    ChallengeFactory challengeFactory;

    @Override
    public ChallengeResponse getById(UUID id) {
        return toApiResponse(challengeRepository.getById(id));
    }

    @Override
    public List<ChallengeResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(challengeRepository.findAll(pageable).getContent());
    }

    @Override
    public List<ChallengeResponse> getBulkById(Set<UUID> idSet) {
        return toApiResponseList(challengeRepository.findAllById(idSet));
    }

    @Override
    public ChallengeResponse create(ChallengeRequest objectRequest) {
        Challenge challenge = challengeFactory.getObject(objectRequest);
        return toApiResponse(challengeRepository.save(challenge));
    }

    @Override
    public List<ChallengeResponse> createBulk(List<ChallengeRequest> objectRequestList) {
        List<Challenge> challengeList = new ArrayList<>();

        for (ChallengeRequest objectRequest : objectRequestList) {
            challengeList.add(challengeFactory.getObject(objectRequest));
        }

        return toApiResponseList(challengeRepository.saveAll(challengeList));
    }

    @Override
    public ChallengeResponse updateById(UUID id, ChallengeRequest challengeRequest) {
        Challenge challenge = challengeRepository.getById(id);

        challenge = applyPatch(challenge, challengeRequest);

        return toApiResponse(challengeRepository.save(challenge));
    }

    @Override
    public List<ChallengeResponse> updateBulkById(Map<UUID, ChallengeRequest> idChallengeRequestMap) {
        List<Challenge> challengeList = challengeRepository.findAllById(idChallengeRequestMap.keySet());

        for (Challenge challenge : challengeList) {
            challenge = applyPatch(challenge, idChallengeRequestMap.get(challenge.getId()));
        }

        return toApiResponseList(challengeRepository.saveAll(challengeList));
    }

    @Override
    public ApiResponse<?> deleteById(UUID id) {
        challengeRepository.deleteById(id);
        return new ApiResponse<>("Challenge has been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        challengeRepository.deleteAll();
        return new ApiResponse<>("Challenges have been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<UUID> idSet) {
        challengeRepository.deleteAllById(idSet);
        return new ApiResponse<>("All Challenges have been deleted successfully");
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return false;
    }

    @Override
    public Challenge applyPatch(Challenge challenge, ChallengeRequest challengeRequest) {
        return challenge;
    }

    @Override
    public ChallengeResponse toApiResponse(Challenge object) {
        return new ChallengeResponse(object);
    }

    @Override
    public List<ChallengeResponse> toApiResponseList(List<Challenge> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
