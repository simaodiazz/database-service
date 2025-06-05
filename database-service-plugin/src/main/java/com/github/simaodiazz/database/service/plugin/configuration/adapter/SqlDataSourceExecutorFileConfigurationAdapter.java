package com.github.simaodiazz.database.service.plugin.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import org.bukkit.configuration.file.FileConfiguration;

public final class SqlDataSourceExecutorFileConfigurationAdapter
		extends SqlFileConfigurationAdapter {

	private static final SqlDataSourceExecutorFileConfigurationAdapter INSTANCE;

	static {
		INSTANCE = new SqlDataSourceExecutorFileConfigurationAdapter();
	}

	@Override
	public SqlConfiguration adapt(FileConfiguration fileConfiguration) {
		final SqlConfiguration configuration = new SqlConfiguration();
		setIfPresentBoolean(
				configuration,
				SqlConfigurationKeys.Executor.ENABLE_ASYNC,
				fileConfiguration,
				"executor.enableAsync");
		setIfPresentInt(
				configuration,
				SqlConfigurationKeys.Executor.Async.MAX_THREAD_POOL_SIZE,
				fileConfiguration,
				"executor.async.maxThreadPoolSize");
		return configuration;
	}

	public static SqlDataSourceExecutorFileConfigurationAdapter getInstance() {
		return INSTANCE;
	}
}
