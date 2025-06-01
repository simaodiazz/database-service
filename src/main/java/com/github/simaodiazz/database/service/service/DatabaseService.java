package com.github.simaodiazz.database.service.service;

import com.github.simaodiazz.database.service.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.wrapper.DataSourceWrapperAware;
import com.github.simaodiazz.database.service.wrapper.PreparedStatementWrapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface DatabaseService extends DataSourceWrapperAware {

    void write(String query);

    Future<Void> writeAsync(String query);

    void write(String query, Consumer<PreparedStatementWrapper> consumer);

    Future<Void> writeAsync(String query, Consumer<PreparedStatementWrapper> consumer);

    <T> Optional<T> readOptional(String query, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<T> readOptionalAsync(String query, SqlRowAdapter<T> adapter);

    <T> Optional<T> readOptional(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<T> readOptionalAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

    <T> List<T> readList(String query, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<List<T>> readListAsync(String query, SqlRowAdapter<T> adapter);

    <T> List<T> readList(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<List<T>> readListAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

    <T> Set<T> readSet(String query, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<Set<T>> readSetAsync(String query, SqlRowAdapter<T> adapter);

    <T> Set<T> readSet(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

    <T> CompletableFuture<Set<T>> readSetAsync(String query, Consumer<PreparedStatementWrapper> consumer, SqlRowAdapter<T> adapter);

}
