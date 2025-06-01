package com.github.simaodiazz.database.service.converter;

public interface SqlColumnConverter<X, Y> {

    Y convertToColumn(X value);

    X convertToObject(Y value);

}
