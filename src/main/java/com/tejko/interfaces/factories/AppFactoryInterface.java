package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.AppRequest;

public interface AppFactoryInterface extends DatabaseEntityFactory<App, AppRequest> {

}
