package com.github.simaodiazz.database.service.plugin.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import org.bukkit.configuration.file.FileConfiguration;

public final class SqlDataSourceWrapperFileConfigurationAdapter
		extends SqlFileConfigurationAdapter {

	private static final SqlDataSourceWrapperFileConfigurationAdapter INSTANCE;

	static {
		INSTANCE = new SqlDataSourceWrapperFileConfigurationAdapter();
	}

	@Override
	public SqlConfiguration adapt(FileConfiguration fileConfiguration) {
		final SqlConfiguration configuration = new SqlConfiguration();

		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.JDBC_URL,
				fileConfiguration.getString("dataSource.hikari.jdbcUrl"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.DRIVER_CLASS_NAME,
				fileConfiguration.getString("dataSource.hikari.driverClassName"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.USERNAME,
				fileConfiguration.getString("dataSource.hikari.username"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.PASSWORD,
				fileConfiguration.getString("dataSource.hikari.password"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.POOL_NAME,
				fileConfiguration.getString("dataSource.hikari.poolName"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.CONNECTION_TEST_QUERY,
				fileConfiguration.getString("dataSource.hikari.connectionTestQuery"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.CATALOG,
				fileConfiguration.getString("dataSource.hikari.catalog"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.SCHEMA,
				fileConfiguration.getString("dataSource.hikari.schema"));
		setIfPresent(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.DATA_SOURCE_CLASS_NAME,
				fileConfiguration.getString("dataSource.hikari.dataSourceClassName"));

		setIfPresentInt(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.MAX_POOL_SIZE,
				fileConfiguration,
				"dataSource.hikari.maximumPoolSize");
		setIfPresentInt(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.MIN_IDLE,
				fileConfiguration,
				"dataSource.hikari.minimumIdle");

		setIfPresentLong(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.CONNECTION_TIMEOUT,
				fileConfiguration,
				"dataSource.hikari.connectionTimeout");
		setIfPresentLong(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.IDLE_TIMEOUT,
				fileConfiguration,
				"dataSource.hikari.idleTimeout");
		setIfPresentLong(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.MAX_LIFETIME,
				fileConfiguration,
				"dataSource.hikari.maxLifetime");
		setIfPresentLong(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.VALIDATION_TIMEOUT,
				fileConfiguration,
				"dataSource.hikari.validationTimeout");
		setIfPresentLong(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.LEAK_DETECTION_THRESHOLD,
				fileConfiguration,
				"dataSource.hikari.leakDetectionThreshold");

		setIfPresentBoolean(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.AUTO_COMMIT,
				fileConfiguration,
				"dataSource.hikari.autoCommit");
		setIfPresentBoolean(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.ISOLATE_INTERNAL_QUERIES,
				fileConfiguration,
				"dataSource.hikari.isolateInternalQueries");
		setIfPresentBoolean(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.ALLOW_POOL_SUSPENSION,
				fileConfiguration,
				"dataSource.hikari.allowPoolSuspension");
		setIfPresentBoolean(
				configuration,
				SqlConfigurationKeys.DataSource.Hikari.READ_ONLY,
				fileConfiguration,
				"dataSource.hikari.readOnly");

		return configuration;
	}

	public static SqlDataSourceWrapperFileConfigurationAdapter getInstance() {
		return INSTANCE;
	}
}
