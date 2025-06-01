package com.github.simaodiazz.database.service.service;

import com.github.simaodiazz.database.service.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.wrapper.PreparedStatementWrapper;
import com.github.simaodiazz.database.service.wrapper.ResultSetWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public final class DatabaseBukkitService implements DatabaseService {

    private DataSourceWrapper source;
    private final ExecutorService executorService;

    public DatabaseBukkitService() {
        this.source = null;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public DatabaseBukkitService(DataSourceWrapper source) {
        this.source = source;
        this.executorService = Executors.newFixedThreadPool(10); // This is enough for me
    }

    @Override
    public void write(String query) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Void> writeAsync(String query) {
        return CompletableFuture.runAsync(() -> write(query), executorService);
    }

    @Override
    public void write(String query, Consumer<PreparedStatementWrapper> consumer) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement)) {
            consumer.accept(wrapper);
            wrapper.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Void> writeAsync(String query, Consumer<PreparedStatementWrapper> consumer) {
        return CompletableFuture.runAsync(() -> write(query, consumer), executorService);
    }

    @Override
    public <T> Optional<T> readOptional(String query, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement);
             ResultSetWrapper resultSet = wrapper.query()) {
            return adapter.optional(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<T> readOptionalAsync(String query, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readOptional(query, adapter).orElse(null), executorService);
    }

    @Override
    public <T> Optional<T> readOptional(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement)) {
            consumer.accept(wrapper);
            try (ResultSetWrapper resultSet = wrapper.query()) {
                return adapter.optional(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<T> readOptionalAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readOptional(query, consumer, adapter).orElse(null), executorService);
    }

    @Override
    public <T> List<T> readList(String query, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement);
             ResultSetWrapper resultSet = wrapper.query()) {
            return adapter.list(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<List<T>> readListAsync(String query, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readList(query, adapter), executorService);
    }

    @Override
    public <T> List<T> readList(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement)) {
            consumer.accept(wrapper);
            try (ResultSetWrapper resultSet = wrapper.query()) {
                return adapter.list(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<List<T>> readListAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readList(query, consumer, adapter), executorService);
    }

    @Override
    public <T> Set<T> readSet(String query, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement);
             ResultSetWrapper resultSet = wrapper.query()) {
            return adapter.set(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<Set<T>> readSetAsync(String query, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readSet(query, adapter), executorService);
    }

    @Override
    public <T> Set<T> readSet(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatementWrapper wrapper = new PreparedStatementWrapper(statement)) {
            consumer.accept(wrapper);
            try (ResultSetWrapper resultSet = wrapper.query()) {
                return adapter.set(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<Set<T>> readSetAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> readSet(query, consumer, adapter), executorService);
    }

    @Override
    public void setDataSourceWrapper(DataSourceWrapper source) {
        this.source = source;
    }
}
