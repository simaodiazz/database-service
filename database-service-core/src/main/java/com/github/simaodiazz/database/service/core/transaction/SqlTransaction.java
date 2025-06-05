package com.github.simaodiazz.database.service.core.transaction;

public interface SqlTransaction extends AutoCloseable {

    void createSavepoint(String name);

    void commit();

    void rollback();

    void rollback(String name);

    void releaseSavepoint(String name);

    @Override
    void close();

}
