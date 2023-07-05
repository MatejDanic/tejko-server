package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.responses.AppResponse;

public interface AppMapperInterface extends DatabaseEntityMapper<App, AppResponse> {
    
}
