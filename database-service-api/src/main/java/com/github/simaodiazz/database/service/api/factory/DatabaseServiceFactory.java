package com.github.simaodiazz.database.service.api.factory;

import com.github.simaodiazz.database.service.api.service.DatabaseService;
import com.github.simaodiazz.database.service.api.service.DefaultDatabaseService;
import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.factory.DataSourceExecutorFactory;

public interface DatabaseServiceFactory {

    static DatabaseService createDatabaseService(SqlConfiguration configuration) {
        final DataSourceExecutor executor = DataSourceExecutorFactory.create(configuration);
        return new DefaultDatabaseService(executor);
    }
}
