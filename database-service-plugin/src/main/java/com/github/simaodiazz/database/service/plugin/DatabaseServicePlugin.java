package com.github.simaodiazz.database.service.plugin;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.factory.DataSourceExecutorFactory;
import com.github.simaodiazz.database.service.plugin.configuration.adapter.SqlDataSourceExecutorFileConfigurationAdapter;
import com.github.simaodiazz.database.service.plugin.configuration.adapter.SqlDataSourceWrapperFileConfigurationAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class DatabaseServicePlugin extends JavaPlugin {

	private DataSourceExecutor executor;

	@Override
	public void onLoad() {
		saveDefaultConfig();
	}

	@Override
	public void onEnable() {
		final FileConfiguration fileConfiguration = getConfig();
		final SqlConfiguration dataSourceWrapperConfiguration =
				SqlDataSourceWrapperFileConfigurationAdapter.getInstance().adapt(fileConfiguration);

		final SqlConfiguration dataSourceExecutorConfiguration =
				SqlDataSourceExecutorFileConfigurationAdapter.getInstance().adapt(fileConfiguration);

		executor =
				DataSourceExecutorFactory.create(
						dataSourceWrapperConfiguration, dataSourceExecutorConfiguration);

		getServer()
				.getServicesManager()
				.register(DataSourceExecutor.class, executor, this, ServicePriority.Highest);

		executor.writeTransactional((transaction) -> {
			executor.write(
					"CREATE TABLE IF NOT EXISTS `test` (`id` INT NOT NULL, `name` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`) );");

			executor.write(
					"CREATE TABLE IF NOT EXISTS `test2` (`id` INT NOT NULL, `name` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`) );");
		});
	}

	@Override
	public void onDisable() {
		executor.close();
	}
}
