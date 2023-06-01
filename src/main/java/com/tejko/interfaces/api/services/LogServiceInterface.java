package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tejko.models.general.Log;

public interface LogServiceInterface {

    public Log getById(UUID id);

    public List<Log> getAll(Integer page, Integer size, String sort, String direction);

    public List<Log> getBulkById(Set<UUID> idSet);

    public void deleteById(UUID id);

    public void deleteAll();

    public List<Log> getBulkByIdIn(Set<UUID> idSet);

}
