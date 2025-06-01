package com.github.simaodiazz.database.service.initializer;

import com.github.simaodiazz.database.service.service.DatabaseBukkitService;
import com.github.simaodiazz.database.service.service.DatabaseService;
import com.github.simaodiazz.database.service.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.wrapper.HikariDataSourceWrapper;
import com.github.simaodiazz.database.service.wrapper.configuration.DataSourceConfigurationBuilder;
import com.github.simaodiazz.database.service.wrapper.configuration.keys.HikariDataSourceKeysConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Properties;

public final class DatabaseServiceInitializer {

    public static DatabaseService initialize(Plugin plugin) {
        final Properties properties = DataSourceConfigurationBuilder.hikari()
            .set(HikariDataSourceKeysConfiguration.JDBC_URL, plugin.getConfig().getString("database.url"))
            .set(HikariDataSourceKeysConfiguration.USERNAME, plugin.getConfig().getString("database.username"))
            .set(HikariDataSourceKeysConfiguration.PASSWORD, plugin.getConfig().getString("database.password"))
            .set(HikariDataSourceKeysConfiguration.DRIVER_CLASS_NAME, plugin.getConfig().getString("database.driver"))
            .set(HikariDataSourceKeysConfiguration.MAX_POOL_SIZE, plugin.getConfig().getInt("database.max-pool-size"))
            .build();

        final DataSourceWrapper dataSourceWrapper = new HikariDataSourceWrapper(properties);

        return new DatabaseBukkitService(dataSourceWrapper);
    }
}