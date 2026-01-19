package com.github.simaodiazz.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class Database {

    private static final SessionConfiguration DEFAULT_PROPERTIES = SessionConfiguration
        .builder()
        .autoCommit(true)
        .readOnly(false)
        .transactionLevel(TransactionLevel.READ_COMMITTED)
        .build();

    private final HikariDataSource source;

    public Database(DatabaseConfiguration configuration) {
        this.source = new HikariDataSource();
        source.setJdbcUrl(configuration.url);
        source.setUsername(configuration.username);
        source.setPassword(configuration.password);
        source.setMaximumPoolSize(configuration.maxPoolSize);
        source.setConnectionTimeout(configuration.connectionTimeout);
    }

    public Session with(SessionConfiguration configuration) {
        try {
            final Connection connection = source.getConnection();
            connection.setAutoCommit(configuration.autoCommit);
            connection.setReadOnly(configuration.readOnly);
            connection.setTransactionIsolation(configuration.transactionLevel.level);
            return new Session(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Session withDefault() {
        return with(DEFAULT_PROPERTIES);
    }

    public void close() {
        source.close();
    }
}
