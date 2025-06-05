package com.github.simaodiazz.database.service.api.service;

import com.github.simaodiazz.database.service.api.query.Query;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;

public class DefaultDatabaseService implements DatabaseService {

    private DataSourceExecutor executor;

    public DefaultDatabaseService(DataSourceExecutor executor) {
        this.executor = executor;
    }

    @Override
    public DataSourceExecutor getExecutor() {
        return executor;
    }

    @Override
    public boolean executeTestsBeforeStartup() {
        return false;
    }

    @Override
    public <T> Query<T> createQuery(Class<T> type) {
        return new Query<>(executor);
    }

    @Override
    public void close() {
        executor.close();
    }

    @Override
    public void setDataSourceExecutor(DataSourceExecutor dataSourceExecutor) {
        this.executor = dataSourceExecutor;
    }
}
