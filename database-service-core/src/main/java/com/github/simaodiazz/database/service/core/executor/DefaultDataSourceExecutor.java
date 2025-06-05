package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;
import com.github.simaodiazz.database.service.core.configuration.SqlConfigurationKeys;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public final class DefaultDataSourceExecutor extends AbstractDataSourceExecutor {

	private final boolean asyncEnabled;
	private ExecutorService executorService;

	public DefaultDataSourceExecutor(
			SqlConfiguration sqlDataSourceExecutorConfiguration, DataSourceWrapper source) {
		super(source);

		this.asyncEnabled =
				sqlDataSourceExecutorConfiguration.getPropertyOrDefault(
						SqlConfigurationKeys.Executor.Async.ENABLE_ASYNC);

		sqlDataSourceExecutorConfiguration.getProperties().keySet().forEach(System.out::println);

		if (this.asyncEnabled) {
			final int maxThreadPoolSize =
					sqlDataSourceExecutorConfiguration.getPropertyOrDefault(
							SqlConfigurationKeys.Executor.Async.MAX_THREAD_POOL_SIZE);
			executorService = Executors.newFixedThreadPool(maxThreadPoolSize);
		}
	}

	@Override
	public CompletableFuture<Void> writeAsync(String query) {
		if (asyncEnabled)
			return CompletableFuture.runAsync(() -> write(query), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public CompletableFuture<Void> writeAsync(
			String query, Consumer<PreparedStatementWrapper> consumer) {
		if (asyncEnabled)
			return CompletableFuture.runAsync(() -> write(query, consumer), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> CompletableFuture<Optional<T>> readOptionalAsync(
			String query, SqlRowAdapter<T> adapter) {
		if (asyncEnabled)
			return CompletableFuture.supplyAsync(() -> readOptional(query, adapter), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> CompletableFuture<Optional<T>> readOptionalAsync(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
		if (asyncEnabled)
			return CompletableFuture.supplyAsync(
					() -> readOptional(query, adapter, consumer), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> CompletableFuture<CollectionResult<T>> readAllAsync(
			String query, SqlRowAdapter<T> adapter) {
		if (asyncEnabled)
			return CompletableFuture.supplyAsync(() -> readAll(query, adapter), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> CompletableFuture<CollectionResult<T>> readAllAsync(
			String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
		if (asyncEnabled)
			return CompletableFuture.supplyAsync(
					() -> readAll(query, adapter, consumer), executorService);
		throw new UnsupportedOperationException();
	}

	@Override
	public CompletableFuture<Void> writeTransactionalAsync(
			String query, Consumer<SqlTransaction> statement) {
		if (asyncEnabled)
			return CompletableFuture.runAsync(
					() -> writeTransactional(query, statement), executorService);
		throw new UnsupportedOperationException();
	}
}
