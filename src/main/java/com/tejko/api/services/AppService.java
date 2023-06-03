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

import com.tejko.api.repositories.ScoreRepository;
import com.tejko.factories.AppFactory;
import com.tejko.interfaces.api.services.AppServiceInterface;
import com.tejko.api.repositories.AppRepository;
import com.tejko.models.general.Score;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.AppRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.AppResponse;

@Service
public class AppService implements AppServiceInterface {

    @Resource
    AppFactory appFactory;

    @Autowired
    AppRepository appRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public AppResponse getById(Integer id) {
        return toApiResponse(appRepository.getById(id));
    }

    @Override
    public List<AppResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(appRepository.findAll(pageable).getContent());
    }

    @Override
    public List<AppResponse> getBulkById(Set<Integer> idSet) {
        return toApiResponseList(appRepository.findAllById(idSet));
    }

    @Override
    public AppResponse create(AppRequest objectRequest) {
        App app = appFactory.getObject(objectRequest);
        return toApiResponse(appRepository.save(app));
    }

    @Override
    public List<AppResponse> createBulk(List<AppRequest> objectRequestList) {
        List<App> appList = new ArrayList<>();

        for (AppRequest objectRequest : objectRequestList) {
            appList.add(appFactory.getObject(objectRequest));
        }

        return toApiResponseList(appRepository.saveAll(appList));
    }

    @Override
    public AppResponse updateById(Integer id, AppRequest appRequest) {
        App app = appRepository.getById(id);

        app = applyPatch(app, appRequest);

        return toApiResponse(appRepository.save(app));
    }

    @Override
    public List<AppResponse> updateBulkById(Map<Integer, AppRequest> idAppRequestMap) {
        List<App> appList = appRepository.findAllById(idAppRequestMap.keySet());

        for (App app : appList) {
            app = applyPatch(app, idAppRequestMap.get(app.getId()));
        }

        return toApiResponseList(appRepository.saveAll(appList));
    }

    @Override
    public ApiResponse<?> deleteById(Integer id) {
        appRepository.deleteById(id);
        return new ApiResponse<>("App has been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<Integer> idSet) {
        appRepository.deleteAllById(idSet);
        return new ApiResponse<>("Apps have been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        appRepository.deleteAll();
        return new ApiResponse<>("All Apps have been deleted successfully");
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
    public App applyPatch(App app, AppRequest appRequest) {
        if (appRequest.getDescription() != null) {
            app.setDescription(appRequest.getDescription());
        }

        return app;
    }

    @Override
    public AppResponse toApiResponse(App object) {
        return new AppResponse(object);
    }

    @Override
    public List<AppResponse> toApiResponseList(List<App> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
