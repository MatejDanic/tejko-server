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

import com.tejko.api.repositories.ChallengeRepository;
import com.tejko.factories.ChallengeFactory;
import com.tejko.interfaces.api.services.ChallengeServiceInterface;
import com.tejko.mappers.ChallengeMapper;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

@Service
public class ChallengeService implements ChallengeServiceInterface {

    @Autowired
    ChallengeRepository challengeRepository;

    @Resource
    ChallengeFactory challengeFactory;

    @Resource
    ChallengeMapper challengeMapper;

    @Override
    public ChallengeResponse getById(UUID id) {
        return challengeMapper.toApiResponse(challengeRepository.getById(id));
    }

    @Override
    public List<ChallengeResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return challengeMapper.toApiResponseList(challengeRepository.findAll(pageable).getContent());
    }

    @Override
    public List<ChallengeResponse> getBulkById(Set<UUID> idSet) {
        return challengeMapper.toApiResponseList(challengeRepository.findAllById(idSet));
    }

    @Override
    public ChallengeResponse create(ChallengeRequest challengeRequest) {
        Challenge challenge = challengeFactory.getObject(challengeRequest);
        return challengeMapper.toApiResponse(challengeRepository.save(challenge));
    }

    @Override
    public List<ChallengeResponse> createBulk(List<ChallengeRequest> objectRequestList) {
        List<Challenge> challengeList = new ArrayList<>();

        for (ChallengeRequest objectRequest : objectRequestList) {
            challengeList.add(challengeFactory.getObject(objectRequest));
        }

        return challengeMapper.toApiResponseList(challengeRepository.saveAll(challengeList));
    }

    @Override
    public ChallengeResponse updateById(UUID id, ChallengeRequest challengeRequest) {
        Challenge challenge = challengeRepository.getById(id);

        challenge = applyPatch(challenge, challengeRequest);

        return challengeMapper.toApiResponse(challengeRepository.save(challenge));
    }

    @Override
    public List<ChallengeResponse> updateBulkById(Map<UUID, ChallengeRequest> idChallengeRequestMap) {
        List<Challenge> challengeList = challengeRepository.findAllById(idChallengeRequestMap.keySet());

        for (Challenge challenge : challengeList) {
            challenge = applyPatch(challenge, idChallengeRequestMap.get(challenge.getId()));
        }

        return challengeMapper.toApiResponseList(challengeRepository.saveAll(challengeList));
    }

    @Override
    public void deleteById(UUID id) {
        challengeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        challengeRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        challengeRepository.deleteAllById(idSet);
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return false;
    }

    @Override
    public Challenge applyPatch(Challenge challenge, ChallengeRequest challengeRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toApiResponseList'");
    }

}
