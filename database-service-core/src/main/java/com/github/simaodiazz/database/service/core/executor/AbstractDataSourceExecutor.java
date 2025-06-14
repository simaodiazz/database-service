package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import com.github.simaodiazz.database.service.core.wrapper.ResultSetWrapper;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractDataSourceExecutor implements DataSourceExecutor {

    protected DataSourceWrapper source;

    public AbstractDataSourceExecutor(DataSourceWrapper source) {
        this.source = source;
    }

    @Override
    public void write(String query) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String query, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query)) {
            consumer.accept(statement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> readOptional(String query, SqlRowAdapter<T> adapter) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query);
             ResultSetWrapper resultSet = statement.query()) {
            return adapter.single(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> readOptional(
        String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query)) {
            consumer.accept(statement);
            final ResultSetWrapper resultSet = statement.query();
            return adapter.single(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CollectionResult<T> readAll(String query, SqlRowAdapter<T> adapter) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query);
             ResultSetWrapper resultSet = statement.query()) {
            final List<T> list = adapter.many(resultSet);
            return new CollectionResult<>(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CollectionResult<T> readAll(
        String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection();
             PreparedStatementWrapper statement = connection.prepareStatement(query)) {
            consumer.accept(statement);
            final ResultSetWrapper resultSet = statement.query();
            final List<T> list = adapter.many(resultSet);
            return new CollectionResult<>(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
        this.source = dataSourceWrapper;
    }

    @Override
    public void close() {
        source.close();
    }
}
