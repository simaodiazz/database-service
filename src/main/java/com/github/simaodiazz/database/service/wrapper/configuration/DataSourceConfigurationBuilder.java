package com.github.simaodiazz.database.service.wrapper.configuration;

import java.util.Properties;

public interface DataSourceConfigurationBuilder {

    Properties build();

    static HikariDataSourceConfigurationBuilder hikari() {
        return new HikariDataSourceConfigurationBuilder();
    }
}
