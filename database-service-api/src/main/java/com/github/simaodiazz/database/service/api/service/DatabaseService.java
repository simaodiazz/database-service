package com.github.simaodiazz.database.service.api.service;

import com.github.simaodiazz.database.service.api.query.Query;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutorAware;

public interface DatabaseService extends DataSourceExecutorAware, AutoCloseable {

    /**
     * Returns the executor used by the service to interact with the database
     */
    DataSourceExecutor getExecutor();

    /**
     * This method will be called before the service starts
     * @return true if the tests executed with success
     */
    boolean executeTestsBeforeStartup();

    /**+
     * Used to create a new query
     */
    <T> Query<T> createQuery(Class<T> type);

    @Override
    void close();

}
