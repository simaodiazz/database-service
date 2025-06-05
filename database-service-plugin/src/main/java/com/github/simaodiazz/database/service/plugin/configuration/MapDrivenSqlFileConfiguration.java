package com.github.simaodiazz.database.service.plugin.configuration;

import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKey;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import java.util.HashMap;
import java.util.Map;

public final class MapDrivenSqlFileConfiguration {

	public static final Map<String, SqlConfigurationKey<?>> FIELDS;

	static {
		FIELDS = new HashMap<>();

		FIELDS.put("dataSource.provider", SqlConfigurationKeys.DataSource.PROVIDER);

		FIELDS.put("dataSource.hikari.driver", SqlConfigurationKeys.DataSource.Hikari.DRIVER);
		FIELDS.put("dataSource.hikari.jdbcUrl", SqlConfigurationKeys.DataSource.Hikari.JDBC_URL);
		FIELDS.put("dataSource.hikari.username", SqlConfigurationKeys.DataSource.Hikari.USERNAME);
		FIELDS.put("dataSource.hikari.password", SqlConfigurationKeys.DataSource.Hikari.PASSWORD);
		FIELDS.put(
				"dataSource.hikari.maximumPoolSize", SqlConfigurationKeys.DataSource.Hikari.MAX_POOL_SIZE);
		FIELDS.put("dataSource.hikari.minimumIdle", SqlConfigurationKeys.DataSource.Hikari.MIN_IDLE);
		FIELDS.put(
				"dataSource.hikari.connectionTimeout",
				SqlConfigurationKeys.DataSource.Hikari.CONNECTION_TIMEOUT);
		FIELDS.put(
				"dataSource.hikari.idleTimeout", SqlConfigurationKeys.DataSource.Hikari.IDLE_TIMEOUT);
		FIELDS.put(
				"dataSource.hikari.maxLifetime", SqlConfigurationKeys.DataSource.Hikari.MAX_LIFETIME);
		FIELDS.put(
				"dataSource.hikari.validationTimeout",
				SqlConfigurationKeys.DataSource.Hikari.VALIDATION_TIMEOUT);
		FIELDS.put(
				"dataSource.hikari.leakDetectionThreshold",
				SqlConfigurationKeys.DataSource.Hikari.LEAK_DETECTION_THRESHOLD);
		FIELDS.put("dataSource.hikari.poolName", SqlConfigurationKeys.DataSource.Hikari.POOL_NAME);
		FIELDS.put(
				"dataSource.hikari.connectionTestQuery",
				SqlConfigurationKeys.DataSource.Hikari.CONNECTION_TEST_QUERY);
		FIELDS.put("dataSource.hikari.autoCommit", SqlConfigurationKeys.DataSource.Hikari.AUTO_COMMIT);
		FIELDS.put(
				"dataSource.hikari.isolateInternalQueries",
				SqlConfigurationKeys.DataSource.Hikari.ISOLATE_INTERNAL_QUERIES);
		FIELDS.put(
				"dataSource.hikari.allowPoolSuspension",
				SqlConfigurationKeys.DataSource.Hikari.ALLOW_POOL_SUSPENSION);
		FIELDS.put("dataSource.hikari.readOnly", SqlConfigurationKeys.DataSource.Hikari.READ_ONLY);
		FIELDS.put("dataSource.hikari.catalog", SqlConfigurationKeys.DataSource.Hikari.CATALOG);
		FIELDS.put("dataSource.hikari.schema", SqlConfigurationKeys.DataSource.Hikari.SCHEMA);
		FIELDS.put(
				"dataSource.hikari.dataSourceClassName",
				SqlConfigurationKeys.DataSource.Hikari.DATA_SOURCE_CLASS_NAME);

		// Executor settings
		FIELDS.put("executor.enableAsync", SqlConfigurationKeys.Executor.ENABLE_ASYNC);
		FIELDS.put(
				"executor.async.maxThreadPoolSize",
				SqlConfigurationKeys.Executor.Async.MAX_THREAD_POOL_SIZE);
	}

	private MapDrivenSqlFileConfiguration() {}
}
