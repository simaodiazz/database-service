package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface DataSourceTransactionExecutor {

    void writeTransactional(String query, Consumer<SqlTransaction> statement);

    Future<Void> writeTransactionalAsync(String query, Consumer<SqlTransaction> statement);

    void writeTransactional(String query, Consumer<PreparedStatementWrapper> statement, Consumer<SqlTransaction> transaction);

    Future<Void> writeTransactionalAsync(String query, Consumer<PreparedStatementWrapper> statement, Consumer<SqlTransaction> transaction);

    <T> Optional<T> readTransactionalOptional(String query, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<Optional<T>> readTransactionalOptionalAsync(String query, SqlRowAdapter<T> adapter);

    <T> Optional<T> readTransactionalOptional(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

    <T> CompletableFuture<Optional<T>> readTransactionalOptionalAsync(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

    <T> List<T> readTransactionalAll(String query, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<List<T>> readTransactionalAllAsync(String query, SqlRowAdapter<T> adapter);

    <T> List<T> readTransactionalAll(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

    <T> CompletableFuture<List<T>> readTransactionalAllAsync(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

}