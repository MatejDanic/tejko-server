package com.tejko.interfaces.factories;

public interface FactoryInterface<T, R> {

    public T create(R objectRequest);

}
