package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public final class DefaultDataSourceExecutor extends AbstractDataSourceExecutor {

    private final boolean asyncEnabled;
    private ExecutorService executorService;

    public DefaultDataSourceExecutor(SqlConfiguration configuration, DataSourceWrapper source) {
        super(source);

        final boolean asyncEnabled = configuration.getPropertyOrDefault(SqlConfigurationKeys.Executor.Async.ENABLE_ASYNC);
        this.asyncEnabled = asyncEnabled;

        if (asyncEnabled) {
            final int maxThreadPoolSize = configuration.getPropertyOrDefault(SqlConfigurationKeys.Executor.Async.MAX_THREAD_POOL_SIZE);
            executorService = Executors.newFixedThreadPool(maxThreadPoolSize);
        }
    }

    @Override
    public Future<Void> writeAsync(String query) {
        if (asyncEnabled)
            return CompletableFuture.runAsync(() -> write(query), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Void> writeAsync(String query, Consumer<PreparedStatementWrapper> consumer) {
        if (asyncEnabled)
            return CompletableFuture.runAsync(() -> write(query, consumer), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> CompletableFuture<Optional<T>> readAsync(String query, SqlRowAdapter<T> adapter) {
        if (asyncEnabled)
            return CompletableFuture.supplyAsync(() -> read(query, adapter), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> CompletableFuture<Optional<T>> readAsync(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        if (asyncEnabled)
            return CompletableFuture.supplyAsync(() -> read(query, adapter, consumer), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> CompletableFuture<List<T>> readAllAsync(String query, SqlRowAdapter<T> adapter) {
        if (asyncEnabled)
            return CompletableFuture.supplyAsync(() -> readAll(query, adapter), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> CompletableFuture<List<T>> readAllAsync(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        if (asyncEnabled)
            return CompletableFuture.supplyAsync(() -> readAll(query, adapter, consumer), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Void> writeTransactionalAsync(String query, Consumer<SqlTransaction> statement) {
        if (asyncEnabled)
            return CompletableFuture.runAsync(() -> writeTransactional(query, statement), executorService);
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Void> writeTransactionalAsync(String query, Consumer<PreparedStatementWrapper> statement, Consumer<SqlTransaction> transaction) {
        if (asyncEnabled)
            return CompletableFuture.runAsync(() -> writeTransactional(query, statement, transaction), executorService);
        throw new UnsupportedOperationException();
    }
}
