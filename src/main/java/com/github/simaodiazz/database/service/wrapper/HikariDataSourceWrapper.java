package com.github.simaodiazz.database.service.wrapper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariDataSourceWrapper implements DataSourceWrapper {

    private final HikariDataSource source;

    public HikariDataSourceWrapper(Properties properties) {
        final HikariConfig hikariConfig = new HikariConfig(properties);
        this.source = new HikariDataSource(hikariConfig);
    }

    @Override
    public Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        source.close();
    }
}
