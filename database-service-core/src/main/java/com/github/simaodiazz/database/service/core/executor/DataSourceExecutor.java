package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapperAware;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface DataSourceExecutor extends DataSourceWrapperAware, AutoCloseable {

	void write(String query);

	CompletableFuture<Void> writeAsync(String query);

	void write(String query, Consumer<PreparedStatementWrapper> consumer);

	CompletableFuture<Void> writeAsync(String query, Consumer<PreparedStatementWrapper> consumer);

	<T> Optional<T> readOptional(String query, SqlRowAdapter<T> adapter);

	<T> CompletableFuture<Optional<T>> readOptionalAsync(String query, SqlRowAdapter<T> adapter);

	<T> Optional<T> readOptional(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

	<T> CompletableFuture<Optional<T>> readOptionalAsync(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

	<T> List<T> readAll(String query, SqlRowAdapter<T> adapter);

	<T> CompletableFuture<List<T>> readAllAsync(String query, SqlRowAdapter<T> adapter);

	<T> List<T> readAll(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

	<T> CompletableFuture<List<T>> readAllAsync(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer);

	void writeTransactional(String query, Consumer<SqlTransaction> statement);

	CompletableFuture<Void> writeTransactionalAsync(String query, Consumer<SqlTransaction> statement);

	@Override
	void close();
}
