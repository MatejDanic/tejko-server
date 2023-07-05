package com.tejko.interfaces;

import java.util.List;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.payload.RestResponse;

public interface DatabaseEntityMapper<T extends DatabaseEntity, S extends RestResponse<T>> {

    public S toRestResponse(T object);

    public List<S> toRestResponseList(List<T> objectList);
    
}
