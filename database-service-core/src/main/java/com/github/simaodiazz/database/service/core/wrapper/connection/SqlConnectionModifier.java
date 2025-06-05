package com.github.simaodiazz.database.service.core.wrapper.connection;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlConnectionModifier {

    void apply(Connection connection);

    /**
     * Modifier to set the connection in read-only mode
     */
    SqlConnectionModifier READ_ONLY = connection -> {
        try {
            connection.setReadOnly(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set the connection in read-write mode
     */
    SqlConnectionModifier READ_WRITE = connection -> {
        try {
            connection.setReadOnly(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set connection in auto-commit mode
     */
    SqlConnectionModifier AUTO_COMMIT = connection -> {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set connection in manual-commit mode
     */
    SqlConnectionModifier MANUAL_COMMIT = connection -> {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set connection in transaction mode
     */
    SqlConnectionModifier TRANSACTION = connection -> {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * to set connection in transaction read-only mode
     */
    SqlConnectionModifier TRANSACTION_READ_ONLY = connection -> {
        try {
            connection.setAutoCommit(false);
            connection.setReadOnly(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set connection in serializable mode
     */
    SqlConnectionModifier TRANSACTION_MAX_ISOLATION = connection -> {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Modifier to set connection in read committed mode
     */
    SqlConnectionModifier TRANSACTION_READ_COMMITED = connection -> {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * This modifier is used to avoid the "dirty read" problem and phantom reads
     */
    SqlConnectionModifier TRANSACTION_REPEATABLE_READ = connection -> {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
}
