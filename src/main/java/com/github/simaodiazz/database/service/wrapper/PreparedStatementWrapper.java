package com.github.simaodiazz.database.service.wrapper;

import com.github.simaodiazz.database.service.converter.SqlColumnConverter;

import java.sql.PreparedStatement;

public class PreparedStatementWrapper implements AutoCloseable {

    private final PreparedStatement statement;

    public PreparedStatementWrapper(PreparedStatement statement) {
        this.statement = statement;
    }

    public void set(int index, Object value) {
        try {
            statement.setObject(index, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <X, Y> void set(int index, X value, SqlColumnConverter<X, Y> converter) {
        try {
            final Y columnValue = converter.convertToColumn(value);
            statement.setObject(index, columnValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSetWrapper query() {
        try {
            return new ResultSetWrapper(statement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute() {
        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
