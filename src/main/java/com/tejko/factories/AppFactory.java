package com.tejko.factories;

import org.springframework.stereotype.Component;
import com.tejko.interfaces.factories.AppFactoryInterface;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.AppRequest;

@Component
public class AppFactory implements AppFactoryInterface {

    @Override
    public App getObject(AppRequest objectRequest) {
        App app = App.create(objectRequest.getId(), objectRequest.getName(), objectRequest.getDescription());
        return app;
    }

}
