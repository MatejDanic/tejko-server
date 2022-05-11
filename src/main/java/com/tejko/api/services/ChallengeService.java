package com.tejko.api.services;

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

import com.tejko.api.repositories.ChallengeRepository;
import com.tejko.factories.ChallengeFactory;
import com.tejko.interfaces.api.services.ChallengeServiceInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;

@Service
public class ChallengeService implements ChallengeServiceInterface {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeFactory challengeFactory;

    @Override
    public Challenge getById(UUID id) {
        return challengeRepository.findById(id).get();
    }

    @Override
    public List<Challenge> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return challengeRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Challenge> getBulkById(Set<UUID> idSet) {
        return challengeRepository.findAllById(idSet);
    }

    @Override
    public Challenge create(ChallengeRequest objectRequest) {
        Challenge challenge = challengeFactory.create(objectRequest);
        return challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> createBulk(List<ChallengeRequest> objectRequestList) {
        List<Challenge> challengeList = new ArrayList<>();

        for (ChallengeRequest objectRequest : objectRequestList) {
            challengeList.add(challengeFactory.create(objectRequest));
        }

        return challengeRepository.saveAll(challengeList);
    }

    @Override
    public Challenge updateById(UUID id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        Challenge challenge = getById(id);

        challenge = applyPatch(challenge, objectPatch);

        return challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<Challenge> challengeList = getBulkById(idObjectPatchMap.keySet());

        for (Challenge challenge : challengeList) {
            challenge = applyPatch(challenge, idObjectPatchMap.get(challenge.getId()));
        }

        return challengeRepository.saveAll(challengeList);
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
    public Challenge applyPatch(Challenge object, JsonPatch patch)
            throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, Challenge.class);
    }

}
