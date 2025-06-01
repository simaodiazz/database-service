package com.github.simaodiazz.database.service.wrapper;

import com.github.simaodiazz.database.service.converter.SqlColumnConverter;
import com.github.simaodiazz.database.service.converter.SqlColumnTypedConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.IllformedLocaleException;

public class ResultSetWrapper implements AutoCloseable {

    private final ResultSet resultSet;

    public ResultSetWrapper(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public boolean next() {
        try {
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getObject(int index) {
        try {
            return resultSet.getObject(index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getObject(int index, Class<T> type) {
        try {
            return resultSet.getObject(index, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <X, Y> X getObject(int index, SqlColumnTypedConverter<X, Y> converter) {
        try {
            Object object = resultSet.getObject(index);
            if (object == null) throw new RuntimeException("Column is null");
            else return converter.cast(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
