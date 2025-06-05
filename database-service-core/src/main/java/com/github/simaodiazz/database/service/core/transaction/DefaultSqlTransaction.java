package com.github.simaodiazz.database.service.core.transaction;

import com.github.simaodiazz.database.service.core.exception.SavepointNotFoundException;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

public final class DefaultSqlTransaction implements SqlTransaction {

    private final Map<String, Savepoint> savePoints;

    private final SqlConnection sqlConnection;
    private final Connection connection;

    public DefaultSqlTransaction(SqlConnection sqlConnection) {
        this.savePoints = new HashMap<>();
        this.sqlConnection = sqlConnection;
        this.connection = sqlConnection.getConnection();
    }

    @Override
    public void createSavepoint(String name) {
        try {
            Savepoint savepoint = connection.setSavepoint(name);
            savePoints.put(name, savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollback(String name) {
        try {
            final Savepoint savepoint = savePoints.get(name);
            if (savepoint == null)
                throw new SavepointNotFoundException("Savepoint " + name + " not found");

            connection.rollback(savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void releaseSavepoint(String name) {
        try {
            final Savepoint savepoint = savePoints.get(name);
            if (savepoint == null)
                throw new SavepointNotFoundException("Savepoint " + name + " not found");

            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        sqlConnection.close();
    }
}
