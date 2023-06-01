package com.tejko.api.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.LogRepository;
import com.tejko.interfaces.api.services.LogServiceInterface;
import com.tejko.models.general.Log;

@Service
public class LogService implements LogServiceInterface {

    @Autowired
    LogRepository logRepository;

    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log getById(UUID id) {
        return logRepository.findById(id).get();
    }

    @Override
    public List<Log> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return logRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Log> getBulkById(Set<UUID> idSet) {
        return logRepository.findAllById(idSet);
    }

    @Override
    public List<Log> getBulkByIdIn(Set<UUID> idList) {
        return logRepository.findAllById(idList);
    }

    @Override
    public void deleteById(UUID id) {
        logRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        logRepository.deleteAll();
    }

}
