package matej.tejko.factories;

import org.springframework.stereotype.Component;
import matej.tejko.interfaces.factories.AppFactoryInterface;
import matej.tejko.models.general.App;
import matej.tejko.models.general.payload.requests.AppRequest;

@Component
public class AppFactory implements AppFactoryInterface {

    @Override
    public App create(AppRequest objectRequest) {

        App app = new App();

        app.setId(objectRequest.getId());
        app.setName(objectRequest.getName());
        app.setDescription(objectRequest.getDescription());

        return app;
    }

}
