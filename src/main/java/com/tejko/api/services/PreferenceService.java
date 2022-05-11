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

import com.tejko.api.repositories.PreferenceRepository;
import com.tejko.factories.PreferenceFactory;
import com.tejko.interfaces.api.services.PreferenceServiceInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;

@Service
public class PreferenceService implements PreferenceServiceInterface {

    @Autowired
    PreferenceFactory preferenceFactory;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Override
    public Preference getById(UUID id) {
        return preferenceRepository.findById(id).get();
    }

    @Override
    public List<Preference> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return preferenceRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Preference> getBulkById(Set<UUID> idSet) {
        return preferenceRepository.findAllById(idSet);
    }

    @Override
    public Preference create(PreferenceRequest objectRequest) {
        Preference preference = preferenceFactory.create(objectRequest);

        return preferenceRepository.save(preference);
    }

    @Override
    public List<Preference> createBulk(List<PreferenceRequest> objectRequestList) {
        List<Preference> preferenceList = new ArrayList<>();

        for (PreferenceRequest objectRequest : objectRequestList) {
            preferenceList.add(preferenceFactory.create(objectRequest));
        }

        return preferenceRepository.saveAll(preferenceList);
    }

    @Override
    public Preference updateById(UUID id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        Preference preference = getById(id);

        preference = applyPatch(preference, objectPatch);

        return preferenceRepository.save(preference);
    }

    @Override
    public List<Preference> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<Preference> preferenceList = getBulkById(idObjectPatchMap.keySet());

        for (Preference preference : preferenceList) {
            preference = applyPatch(preference, idObjectPatchMap.get(preference.getId()));
        }

        preferenceRepository.saveAll(preferenceList);
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        preferenceRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        preferenceRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        preferenceRepository.deleteAllById(idSet);
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return getById(objectId).getUser().getId().equals(userId);
    }

    @Override
    public Preference applyPatch(
            Preference object, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, Preference.class);
    }

}
