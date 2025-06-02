package com.github.simaodiazz.database.service.converter;

import com.github.simaodiazz.database.service.wrapper.PreparedStatementWrapper;

import java.util.function.Consumer;

public interface SqlColumnConverter<X, Y> {

    Y convertToColumn(X value);

    /**
     * Convert directly to column
     * @return the element as column
     */
    default Y convertToColumn(X value, PreparedStatementWrapper wrapper) {
        throw new UnsupportedOperationException("Not implemented");
    }

    X convertToObject(Y value);

}
