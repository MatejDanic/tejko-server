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

import com.tejko.factories.AppFactory;
import com.tejko.interfaces.api.services.AppServiceInterface;
import com.tejko.mappers.AppMapper;
import com.tejko.api.repositories.AppRepository;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.AppResponse;

@Service
public class AppService implements AppServiceInterface {

    @Autowired
    AppRepository appRepository;

    @Resource
    AppFactory appFactory;

    @Resource
    AppMapper appMapper;

    @Autowired
    ScoreService scoreService;

    @Override
    public AppResponse getById(UUID id) {
        return appMapper.toRestResponse(appRepository.getById(id));
    }

    @Override
    public List<AppResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return appMapper.toRestResponseList(appRepository.findAll(pageable).getContent());
    }

    @Override
    public List<AppResponse> getBulkById(Set<UUID> idSet) {
        return appMapper.toRestResponseList(appRepository.findAllById(idSet));
    }

    @Override
    public AppResponse create(AppRequest appRequest) {
        App app = appFactory.getObject(appRequest);
        return appMapper.toRestResponse(appRepository.save(app));
    }

    @Override
    public List<AppResponse> createBulk(List<AppRequest> objectRequestList) {
        List<App> appList = new ArrayList<>();

        for (AppRequest objectRequest : objectRequestList) {
            appList.add(appFactory.getObject(objectRequest));
        }

        return appMapper.toRestResponseList(appRepository.saveAll(appList));
    }

    @Override
    public AppResponse updateById(UUID id, AppRequest appRequest) {
        App app = appRepository.getById(id);

        app = applyPatch(app, appRequest);

        return appMapper.toRestResponse(appRepository.save(app));
    }

    @Override
    public List<AppResponse> updateBulkById(Map<UUID, AppRequest> idAppRequestMap) {
        List<App> appList = appRepository.findAllById(idAppRequestMap.keySet());

        for (App app : appList) {
            app = applyPatch(app, idAppRequestMap.get(app.getId()));
        }

        return appMapper.toRestResponseList(appRepository.saveAll(appList));
    }

    @Override
    public void deleteById(UUID id) {
        appRepository.deleteById(id);
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        appRepository.deleteAllById(idSet);
    }

    @Override
    public void deleteAll() {
        appRepository.deleteAll();
    }

    @Override
    public boolean hasPermission(UUID userId, UUID appId) {
        return false;
    }

    @Override
    public App applyPatch(App app, AppRequest appRequest) {
        if (appRequest.getDescription() != null) {
            app.setDescription(appRequest.getDescription());
        }
        return app;
    }

}
