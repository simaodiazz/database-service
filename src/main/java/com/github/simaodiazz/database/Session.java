package com.github.simaodiazz.database;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

@RequiredArgsConstructor
public final class Session implements AutoCloseable {

    private final Connection connection;

    public <T> ResultCursor handle(String sql, Consumer<Statement> consumer) {
        if (consumer == null)
            return null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final Statement statement = new Statement(preparedStatement);
            consumer.accept(statement);
            return statement.query();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String sql, Consumer<Statement> consumer) {
        handle(sql, consumer);
    }

    public void run(String sql) {
        run(sql, null);
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new RuntimeException("Problem in commit.", exception);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new RuntimeException("Problem in rollback.", exception);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException("Problem closing the session.", exception);
        }
    }
}
