package com.github.simaodiazz.database.service.wrapper.configuration;

import com.github.simaodiazz.database.service.wrapper.configuration.keys.HikariDataSourceKeysConfiguration;

import java.util.Properties;

public final class HikariDataSourceConfigurationBuilder implements DataSourceConfigurationBuilder {

    private final Properties properties;

    public HikariDataSourceConfigurationBuilder() {
        this.properties = new Properties();
    }

    public HikariDataSourceConfigurationBuilder(final Properties properties) {
        this.properties = properties;
    }

    public <T> HikariDataSourceConfigurationBuilder set(HikariDataSourceKeysConfiguration<T> key, Object value) {
        final String name = key.key();
        properties.put(name, String.valueOf(value));
        return this;
    }

    @Override
    public Properties build() {
        return properties;
    }
}
