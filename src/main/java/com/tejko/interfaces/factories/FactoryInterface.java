package com.tejko.interfaces.factories;

public interface FactoryInterface<T, R> {

    public T getObject(R objectRequest);

}
