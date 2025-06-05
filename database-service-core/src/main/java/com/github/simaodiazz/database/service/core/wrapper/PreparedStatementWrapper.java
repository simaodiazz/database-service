package com.github.simaodiazz.database.service.core.wrapper;

public interface PreparedStatementWrapper extends AutoCloseable {

    <T> void setParameter(int parameterIndex, T value);

    void addBatch();

    void execute();

    ResultSetWrapper query();

    @Override
    void close();

}
