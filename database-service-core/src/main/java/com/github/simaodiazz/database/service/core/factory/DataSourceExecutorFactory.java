package com.github.simaodiazz.database.service.core.factory;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.executor.DefaultDataSourceExecutor;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.HikariDataSourceWrapper;
import java.util.Properties;

public final class DataSourceExecutorFactory {

	public static DataSourceExecutor create(
			SqlConfiguration sqlDataSourceWrapperConfiguration,
			SqlConfiguration sqlDataSourceExecutorConfiguration) {
		final DataSourceWrapper dataSourceWrapper =
				createDataSourceWrapper(sqlDataSourceWrapperConfiguration);

		return new DefaultDataSourceExecutor(sqlDataSourceExecutorConfiguration, dataSourceWrapper);
	}

	private static DataSourceWrapper createDataSourceWrapper(SqlConfiguration configuration) {
		final Properties properties = configuration.getProperties();
		return new HikariDataSourceWrapper(properties);
	}
}
