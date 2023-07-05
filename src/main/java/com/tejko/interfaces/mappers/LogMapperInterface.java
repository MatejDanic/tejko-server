package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.Log;
import com.tejko.models.general.payload.responses.LogResponse;

public interface LogMapperInterface extends DatabaseEntityMapper<Log, LogResponse> {
    
}
