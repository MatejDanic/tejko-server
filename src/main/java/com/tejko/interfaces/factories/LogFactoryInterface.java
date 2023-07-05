package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Log;
import com.tejko.models.general.payload.requests.LogRequest;

public interface LogFactoryInterface extends DatabaseEntityFactory<Log, LogRequest> {

}
