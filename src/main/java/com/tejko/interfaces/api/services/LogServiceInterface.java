package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tejko.models.general.payload.requests.LogRequest;
import com.tejko.models.general.payload.responses.LogResponse;

public interface LogServiceInterface {

    public LogResponse create(LogRequest logRequest);

    public LogResponse getById(UUID id);

    public List<LogResponse> getAll(Integer page, Integer size, String sort, String direction);

    public List<LogResponse> getBulkById(Set<UUID> idSet);

    public void deleteById(UUID id);

    public void deleteAll();

    public List<LogResponse> getBulkByIdIn(Set<UUID> idSet);

}
