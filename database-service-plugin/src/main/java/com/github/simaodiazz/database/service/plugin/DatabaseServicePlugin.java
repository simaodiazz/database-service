package com.github.simaodiazz.database.service.plugin;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.factory.DataSourceExecutorFactory;
import com.github.simaodiazz.database.service.plugin.configuration.adapter.SqlFileConfigurationAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseServicePlugin extends JavaPlugin {

	private DataSourceExecutor executor;

	@Override
	public void onLoad() {
		saveDefaultConfig();
	}

	@Override
	public void onEnable() {
		final FileConfiguration fileConfiguration = getConfig();
		final SqlConfiguration sqlConfiguration =
				SqlFileConfigurationAdapter.getInstance().adapt(fileConfiguration);

		executor = DataSourceExecutorFactory.create(sqlConfiguration);

		getServer()
				.getServicesManager()
				.register(DataSourceExecutor.class, executor, this, ServicePriority.Highest);
	}

	@Override
	public void onDisable() {
		executor.close();
	}
}
