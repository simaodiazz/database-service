package com.github.simaodiazz.database.service.core.factory;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.executor.DefaultDataSourceExecutor;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.HikariDataSourceWrapper;

import java.util.Properties;

public final class DataSourceExecutorFactory {

    public static DataSourceExecutor create(SqlConfiguration configuration) {
        final DataSourceWrapper dataSourceWrapper = createDataSourceWrapper(configuration);

        return new DefaultDataSourceExecutor(configuration, dataSourceWrapper);
    }

    private static DataSourceWrapper createDataSourceWrapper(SqlConfiguration configuration) {
        final String dataSourceProvider = configuration.getProperty(SqlConfigurationKeys.DataSource.PROVIDER);
        if (dataSourceProvider == null)
            throw new IllegalArgumentException("Data source provider not configured");

        if (dataSourceProvider.equals("hikari")) {
            final Properties properties = configuration.getProperties();
            return new HikariDataSourceWrapper(properties);
        }

        throw new IllegalArgumentException("Unknown data source provider: " + dataSourceProvider);
    }
}
