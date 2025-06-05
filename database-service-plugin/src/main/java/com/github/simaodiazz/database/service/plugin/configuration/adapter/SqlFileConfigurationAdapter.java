package com.github.simaodiazz.database.service.plugin.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKey;
import com.github.simaodiazz.database.service.core.configuration.adapter.SqlConfigurationAdapter;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class SqlFileConfigurationAdapter
		implements SqlConfigurationAdapter<FileConfiguration> {

	protected <K> void setIfPresent(SqlConfiguration config, SqlConfigurationKey<K> key, K value) {
		if (value == null) return;
		config.setProperty(key, value);
	}

	protected void setIfPresentInt(
			SqlConfiguration config,
			SqlConfigurationKey<Integer> key,
			FileConfiguration fc,
			String path) {
		if (fc.contains(path)) {
			int val = fc.getInt(path);
			if (val < 1) val = key.standard();
			config.setProperty(key, val);
		}
	}

	protected void setIfPresentLong(
			SqlConfiguration config, SqlConfigurationKey<Long> key, FileConfiguration fc, String path) {
		if (fc.contains(path)) {
			long val = fc.getLong(path);
			if (val < 1) val = key.standard();
			config.setProperty(key, val);
		}
	}

	protected void setIfPresentBoolean(
			SqlConfiguration config,
			SqlConfigurationKey<Boolean> key,
			FileConfiguration fc,
			String path) {
		if (fc.contains(path)) {
			boolean val = fc.getBoolean(path);
			config.setProperty(key, val);
		}
	}
}
