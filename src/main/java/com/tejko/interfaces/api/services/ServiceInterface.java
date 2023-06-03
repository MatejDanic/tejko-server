package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.tejko.models.general.payload.responses.ApiResponse;

public interface ServiceInterface<I, T, R, A extends ApiResponse<T>> {

    public A getById(I id);

    public List<A> getBulkById(Set<I> ids);

    public List<A> getAll(Integer page, Integer size, String sort, String direction);

    public A updateById(I id, R objectRequest);

    public List<A> updateBulkById(Map<I, R> idObjectRequestMap);

    public A create(R objectRequest);

    public List<A> createBulk(List<R> objectRequestList);

    public ApiResponse<?> deleteById(I id);

    public ApiResponse<?> deleteBulkById(Set<I> idSet);

    public ApiResponse<?> deleteAll();

    public T applyPatch(T object, R objectRequest);

    public boolean hasPermission(UUID userId, I objectId);

    public A toApiResponse(T object);

    public List<A> toApiResponseList(List<T> objectList);

}
