package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.AppMapperInterface;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.responses.AppResponse;

@Component
public class AppMapper implements AppMapperInterface {

    @Override
    public AppResponse toApiResponse(App app) {
        return new AppResponse(
            app.getId(), 
            app.getCreatedDate(), 
            app.getLastModifiedDate(), 
            app.getName(), 
            app.getDescription()
        );
    }

    @Override
    public List<AppResponse> toApiResponseList(List<App> appList) {
        return appList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
