package com.github.simaodiazz.database.service.plugin.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import com.github.simaodiazz.database.service.core.configuration.adapter.SqlConfigurationAdapter;
import org.bukkit.configuration.file.FileConfiguration;

public class BukkitSqlConfigurationAdapter implements SqlConfigurationAdapter<FileConfiguration> {

    private static final SqlConfigurationAdapter<FileConfiguration> INSTANCE = new BukkitSqlConfigurationAdapter();

    @Override
    public SqlConfiguration adapt(FileConfiguration fileConfiguration) {
        SqlConfiguration sqlConfiguration = new SqlConfiguration();
        sqlConfiguration.setProperty(SqlConfigurationKeys.DataSource.PROVIDER, "hikari");
        sqlConfiguration.setProperty(SqlConfigurationKeys.DataSource.Hikari.JDBC_URL, fileConfiguration.getString("jdbc-url"));
        sqlConfiguration.setProperty(SqlConfigurationKeys.DataSource.Hikari.USERNAME, fileConfiguration.getString("username"));
        sqlConfiguration.setProperty(SqlConfigurationKeys.DataSource.Hikari.PASSWORD, fileConfiguration.getString("password"));
        return sqlConfiguration;
    }

    public static SqlConfigurationAdapter<FileConfiguration> getInstance() {
        return INSTANCE;
    }
}