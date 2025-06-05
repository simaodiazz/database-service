package com.github.simaodiazz.database.service.core.wrapper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;

public final class HikariDataSourceWrapper extends AbstractDataSourceWrapper<HikariDataSource> {

	public HikariDataSourceWrapper() {
		this.source = new HikariDataSource();
	}

	public HikariDataSourceWrapper(final Properties properties) {
		final HikariConfig config = new HikariConfig(properties);
		this.source = new HikariDataSource(config);
	}

	@Override
	public void close() {
		try {
			source.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
