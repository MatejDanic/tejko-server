package com.tejko.interfaces;

import java.util.List;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.payload.responses.ApiResponse;

public interface ObjectMapperInterface<T extends DatabaseEntity, S extends ApiResponse<T>> {

    public S toApiResponse(T object);

    public List<S> toApiResponseList(List<T> objectList);
    
}
