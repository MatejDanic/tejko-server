package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

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

import com.tejko.api.repositories.ScoreRepository;
import com.tejko.factories.AppFactory;
import com.tejko.interfaces.api.services.AppServiceInterface;
import com.tejko.api.repositories.AppRepository;
import com.tejko.models.general.Score;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.AppRequest;

@Service
public class AppService implements AppServiceInterface {

    @Resource
    AppFactory appFactory;

    @Autowired
    AppRepository appRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public App getById(Integer id) {
        return appRepository.findById(id).get();
    }

    @Override
    public List<App> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return appRepository.findAll(pageable).getContent();
    }

    @Override
    public List<App> getBulkById(Set<Integer> idSet) {
        return appRepository.findAllById(idSet);
    }

    @Override
    public App create(AppRequest objectRequest) {
        App app = appFactory.getObject(objectRequest);
        return appRepository.save(app);
    }

    @Override
    public List<App> createBulk(List<AppRequest> objectRequestList) {
        List<App> appList = new ArrayList<>();

        for (AppRequest objectRequest : objectRequestList) {
            appList.add(appFactory.getObject(objectRequest));
        }

        return appRepository.saveAll(appList);
    }

    @Override
    public App updateById(Integer id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        App app = getById(id);

        app = applyPatch(app, objectPatch);

        return appRepository.save(app);
    }

    @Override
    public List<App> updateBulkById(Map<Integer, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<App> appList = getBulkById(idObjectPatchMap.keySet());

        for (App app : appList) {
            app = applyPatch(app, idObjectPatchMap.get(app.getId()));
        }

        return appRepository.saveAll(appList);
    }

    @Override
    public void deleteById(Integer id) {
        appRepository.deleteById(id);
    }

    @Override
    public void deleteBulkById(Set<Integer> idSet) {
        appRepository.deleteAllById(idSet);
    }

    @Override
    public void deleteAll() {
        appRepository.deleteAll();
    }

    @Override
    public List<Score> getScoresByAppId(Integer id) {
        return scoreRepository.findAllByAppId(id);
    }

    @Override
    public boolean hasPermission(UUID userId, Integer objectId) {
        return false;
    }

    @Override
    public App applyPatch(App object, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, App.class);
    }

}
