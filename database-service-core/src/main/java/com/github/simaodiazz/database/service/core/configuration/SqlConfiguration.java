package com.github.simaodiazz.database.service.core.configuration;

import java.util.Objects;
import java.util.Properties;

public final class SqlConfiguration {

    private final Properties properties;

    public SqlConfiguration() {
        this.properties = new Properties();
    }

    public SqlConfiguration(Properties properties) {
        this.properties = properties;
    }

    public <T> void setProperty(SqlConfigurationKey<T> key, T value) {
        Objects.requireNonNull(key);

        final String keyName = key.key();
        properties.put(keyName, value);
    }

    public <T> T getProperty(SqlConfigurationKey<T> key) {
        Objects.requireNonNull(key);

        final String keyName = key.key();
        final Object value = properties.get(keyName);

        if (value == null)
            return null;

        final boolean isInstance = key.type().isInstance(value);
        if (isInstance)
            return key.type().cast(value);

        return null;
    }

    public <T> T getPropertyOrDefault(SqlConfigurationKey<T> key) {
        final T value = getProperty(key);
        return value == null ? key.standard() : value;
    }

    public Properties getProperties() {
        return properties;
    }
}
