package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.LogMapperInterface;
import com.tejko.models.general.Log;
import com.tejko.models.general.payload.responses.LogResponse;

@Component
public class LogMapper implements LogMapperInterface {

    @Override
    public LogResponse toRestResponse(Log log) {
        return new LogResponse(
            log.getId(), 
            log.getCreatedDate(), 
            log.getLastModifiedDate(), 
            log.getUser().getId(), 
            log.getLevel(), 
            log.getContent()
        );
    }

    @Override
    public List<LogResponse> toRestResponseList(List<Log> logList) {
        return logList.stream().map(this::toRestResponse).collect(Collectors.toList());
    }

}
