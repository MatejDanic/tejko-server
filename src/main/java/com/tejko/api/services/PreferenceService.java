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

import com.tejko.api.repositories.PreferenceRepository;
import com.tejko.factories.PreferenceFactory;
import com.tejko.interfaces.api.services.PreferenceServiceInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.PreferenceResponse;

@Service
public class PreferenceService implements PreferenceServiceInterface {

    @Resource
    PreferenceFactory preferenceFactory;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Override
    public PreferenceResponse getById(UUID id) {
        return toApiResponse(preferenceRepository.getById(id));
    }

    @Override
    public List<PreferenceResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(preferenceRepository.findAll(pageable).getContent());
    }

    @Override
    public List<PreferenceResponse> getBulkById(Set<UUID> idSet) {
        return toApiResponseList(preferenceRepository.findAllById(idSet));
    }

    @Override
    public PreferenceResponse create(PreferenceRequest objectRequest) {
        Preference preference = preferenceFactory.getObject(objectRequest);

        return toApiResponse(preferenceRepository.save(preference));
    }

    @Override
    public List<PreferenceResponse> createBulk(List<PreferenceRequest> objectRequestList) {
        List<Preference> preferenceList = new ArrayList<>();

        for (PreferenceRequest objectRequest : objectRequestList) {
            preferenceList.add(preferenceFactory.getObject(objectRequest));
        }

        return toApiResponseList(preferenceRepository.saveAll(preferenceList));
    }

    @Override
    public PreferenceResponse updateById(UUID id, PreferenceRequest preferenceRequest) {
        Preference preference = preferenceRepository.getById(id);

        preference = applyPatch(preference, preferenceRequest);

        return toApiResponse(preferenceRepository.save(preference));
    }

    @Override
    public List<PreferenceResponse> updateBulkById(Map<UUID, PreferenceRequest> idPreferenceRequestMap) {
        List<Preference> preferenceList = preferenceRepository.findAllById(idPreferenceRequestMap.keySet());

        for (Preference preference : preferenceList) {
            preference = applyPatch(preference, idPreferenceRequestMap.get(preference.getId()));
        }

        preferenceRepository.saveAll(preferenceList);
        return null;
    }

    @Override
    public ApiResponse<?> deleteById(UUID id) {
        preferenceRepository.deleteById(id);
        return new ApiResponse<>("Preference has been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        preferenceRepository.deleteAll();
        return new ApiResponse<>("Preferences have been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<UUID> idSet) {
        preferenceRepository.deleteAllById(idSet);
        return new ApiResponse<>("All Preferences have been deleted successfully");
    }

    @Override
    public PreferenceResponse getByUserId(UUID userId) {
        return toApiResponse(preferenceRepository.getByUserId(userId));
    }

    @Override
    public ApiResponse<?> deleteByUserId(UUID userId) {
        preferenceRepository.deleteByUserId(userId);
        return new ApiResponse<>("User's Preference has been deleted successfully");
    }
    
    @Override
    public PreferenceResponse updateByUserId(UUID userId, PreferenceRequest preferenceRequest) {
        Preference preference = preferenceRepository.getByUserId(userId);

        preference = applyPatch(preference, preferenceRequest);

        return toApiResponse(preferenceRepository.save(preference));
    }

    @Override
    public Preference applyPatch(Preference preference, PreferenceRequest preferenceRequest) {

        return preference;
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return preferenceRepository.getById(objectId).getUser().getId().equals(userId);
    }

    @Override
    public PreferenceResponse toApiResponse(Preference object) {
        return new PreferenceResponse(object);
    }

    @Override
    public List<PreferenceResponse> toApiResponseList(List<Preference> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
