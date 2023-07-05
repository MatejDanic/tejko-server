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
import com.tejko.factories.LogFactory;
import com.tejko.interfaces.api.services.LogServiceInterface;
import com.tejko.mappers.LogMapper;
import com.tejko.models.general.Log;
import com.tejko.models.general.payload.requests.LogRequest;
import com.tejko.models.general.payload.responses.LogResponse;

@Service
public class LogService implements LogServiceInterface {

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogFactory logFactory;

    @Autowired
    LogMapper logMapper;

    @Override
    public LogResponse getById(UUID id) {
        return logMapper.toRestResponse(logRepository.findById(id).get());
    }

    @Override
    public List<LogResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return logMapper.toRestResponseList(logRepository.findAll(pageable).getContent());
    }

    @Override
    public List<LogResponse> getBulkById(Set<UUID> idSet) {
        return logMapper.toRestResponseList(logRepository.findAllById(idSet));
    }

    @Override
    public List<LogResponse> getBulkByIdIn(Set<UUID> idList) {
        return logMapper.toRestResponseList(logRepository.findAllById(idList));
    }

    @Override
    public LogResponse create(LogRequest logRequest) {
        Log log = logFactory.getObject(logRequest);
        return logMapper.toRestResponse(logRepository.save(log));
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
