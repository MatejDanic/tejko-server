package com.tejko.interfaces.api;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.tejko.models.general.payload.requests.ApiRequest;
import com.tejko.models.general.payload.responses.ApiResponse;

public interface ObjectServiceInterface<K, T, S extends ApiRequest<?>, U extends ApiResponse<?>> {

    public U getById(K id);

    public List<U> getBulkById(Set<K> ids);

    public List<U> getAll(Integer page, Integer size, String sort, String direction);

    public U updateById(K id, S objectRequest);

    public List<U> updateBulkById(Map<K, S> idObjectRequestMap);

    public U create(S objectRequest);

    public List<U> createBulk(List<S> objectRequestList);

    public void deleteById(K id);

    public void deleteBulkById(Set<K> idSet);

    public void deleteAll();

    public T applyPatch(T object, S objectRequest);

    public boolean hasPermission(UUID userId, K objectId);

}
