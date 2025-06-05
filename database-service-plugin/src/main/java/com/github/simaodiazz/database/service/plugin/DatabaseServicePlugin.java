package com.github.simaodiazz.database.service.plugin;

import com.github.simaodiazz.database.service.api.factory.DatabaseServiceFactory;
import com.github.simaodiazz.database.service.api.service.DatabaseService;
import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.plugin.configuration.adapter.BukkitSqlConfigurationAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseServicePlugin extends JavaPlugin {

    private DatabaseService service;

    @Override
    public void onLoad() {
        saveDefaultConfig();

        final FileConfiguration fileConfiguration = getConfig();
        final SqlConfiguration configuration = BukkitSqlConfigurationAdapter.getInstance().adapt(fileConfiguration);

        service = DatabaseServiceFactory.createDatabaseService(configuration);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        service.close();
    }
}
