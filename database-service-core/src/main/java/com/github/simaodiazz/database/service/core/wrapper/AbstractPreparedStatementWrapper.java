package com.github.simaodiazz.database.service.core.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPreparedStatementWrapper implements PreparedStatementWrapper {

    protected final PreparedStatement statement;

    public AbstractPreparedStatementWrapper(PreparedStatement statement) {
        this.statement = statement;
    }

    @Override
    public void execute() {
        try {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSetWrapper query() {
        try {
            final ResultSet resultSet = statement.executeQuery();
            return new DefaultResultSetWrapper(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void setParameter(int parameterIndex, T value) {
        try {
            statement.setObject(parameterIndex, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBatch() {
        try {
            statement.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
