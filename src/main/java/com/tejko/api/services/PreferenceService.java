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

import com.tejko.api.repositories.PreferenceRepository;
import com.tejko.factories.PreferenceFactory;
import com.tejko.interfaces.api.services.PreferenceServiceInterface;
import com.tejko.mappers.PreferenceMapper;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;

@Service
public class PreferenceService implements PreferenceServiceInterface {

    @Autowired
    PreferenceRepository preferenceRepository;

    @Resource
    PreferenceFactory preferenceFactory;

    @Resource
    PreferenceMapper preferenceMapper;

    @Override
    public PreferenceResponse getById(UUID id) {
        return preferenceMapper.toApiResponse(preferenceRepository.getById(id));
    }

    @Override
    public List<PreferenceResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return preferenceMapper.toApiResponseList(preferenceRepository.findAll(pageable).getContent());
    }

    @Override
    public List<PreferenceResponse> getBulkById(Set<UUID> idSet) {
        return preferenceMapper.toApiResponseList(preferenceRepository.findAllById(idSet));
    }

    @Override
    public PreferenceResponse create(PreferenceRequest objectRequest) {
        Preference preference = preferenceFactory.getObject(objectRequest);

        return preferenceMapper.toApiResponse(preferenceRepository.save(preference));
    }

    @Override
    public List<PreferenceResponse> createBulk(List<PreferenceRequest> objectRequestList) {
        List<Preference> preferenceList = new ArrayList<>();

        for (PreferenceRequest objectRequest : objectRequestList) {
            preferenceList.add(preferenceFactory.getObject(objectRequest));
        }

        return preferenceMapper.toApiResponseList(preferenceRepository.saveAll(preferenceList));
    }

    @Override
    public PreferenceResponse updateById(UUID id, PreferenceRequest preferenceRequest) {
        Preference preference = preferenceRepository.getById(id);

        preference = applyPatch(preference, preferenceRequest);

        return preferenceMapper.toApiResponse(preferenceRepository.save(preference));
    }

    @Override
    public List<PreferenceResponse> updateBulkById(Map<UUID, PreferenceRequest> idPreferenceRequestMap) {
        List<Preference> preferenceList = preferenceRepository.findAllById(idPreferenceRequestMap.keySet());

        for (Preference preference : preferenceList) {
            preference = applyPatch(preference, idPreferenceRequestMap.get(preference.getId()));
        }

        return preferenceMapper.toApiResponseList(preferenceRepository.saveAll(preferenceList));
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
    public PreferenceResponse getByUserId(UUID userId) {
        return preferenceMapper.toApiResponse(preferenceRepository.getByUserId(userId));
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return preferenceRepository.getById(objectId).getUser().getId().equals(userId);
    }
    
    @Override
    public Preference applyPatch(Preference preference, PreferenceRequest preferenceRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toApiResponseList'");
    }

}
