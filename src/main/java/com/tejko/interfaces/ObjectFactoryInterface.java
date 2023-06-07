package com.tejko.interfaces;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.payload.requests.ApiRequest;

public abstract interface ObjectFactoryInterface<T, R extends ApiRequest<? extends DatabaseEntity>> {

    public T getObject(R objectRequest);

}
