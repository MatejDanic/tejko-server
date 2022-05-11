package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public interface ServiceInterface<I, T, R> {

    public T getById(I id);

    public List<T> getBulkById(Set<I> ids);

    public List<T> getAll(Integer page, Integer size, String sort, String direction);

    public T updateById(I id, JsonPatch object) throws JsonProcessingException, JsonPatchException;

    public List<T> updateBulkById(Map<I, JsonPatch> idObjectMap) throws JsonProcessingException, JsonPatchException;

    public T create(R objectRequest);

    public List<T> createBulk(List<R> objectRequestList);

    public void deleteById(I id);

    public void deleteBulkById(Set<I> idSet);

    public void deleteAll();

    public boolean hasPermission(UUID userId, I objectId);

    public T applyPatch(T object, JsonPatch patch) throws JsonPatchException, JsonProcessingException;

}
