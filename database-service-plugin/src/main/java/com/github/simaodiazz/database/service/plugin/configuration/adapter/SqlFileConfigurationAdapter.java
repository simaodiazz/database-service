package com.github.simaodiazz.database.service.plugin.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.adapter.SqlConfigurationAdapter;
import com.github.simaodiazz.database.service.plugin.configuration.MapDrivenSqlFileConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

public final class SqlFileConfigurationAdapter
		implements SqlConfigurationAdapter<FileConfiguration> {

	private static final SqlFileConfigurationAdapter INSTANCE;

	static {
		INSTANCE = new SqlFileConfigurationAdapter();
	}

	@Override
	public SqlConfiguration adapt(FileConfiguration fileConfiguration) {
		final SqlConfiguration sqlConfiguration = new SqlConfiguration();

		MapDrivenSqlFileConfiguration.FIELDS.keySet().stream()
				.filter(fileConfiguration::contains)
				.forEach(MapDrivenSqlFileConfiguration.FIELDS::get);

		return sqlConfiguration;
	}

	public static SqlFileConfigurationAdapter getInstance() {
		return INSTANCE;
	}
}
