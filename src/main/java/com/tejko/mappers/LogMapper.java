package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tejko.interfaces.mappers.LogMapperInterface;
import com.tejko.models.general.Log;
import com.tejko.models.general.payload.responses.LogResponse;

public class LogMapper implements LogMapperInterface {

    @Override
    public LogResponse toApiResponse(Log log) {
        return new LogResponse(
            log.getCreatedDate(), 
            log.getLastModifiedDate(), 
            log.getId(), 
            log.getUser().getId(), 
            log.getLevel(), 
            log.getContent()
        );
    }

    @Override
    public List<LogResponse> toApiResponseList(List<Log> logList) {
        return logList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
