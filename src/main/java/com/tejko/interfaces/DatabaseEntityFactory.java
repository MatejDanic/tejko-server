package com.tejko.interfaces;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.payload.RestRequest;

public abstract interface DatabaseEntityFactory<T, R extends RestRequest<? extends DatabaseEntity>> {

    public T getObject(R objectRequest);

}
