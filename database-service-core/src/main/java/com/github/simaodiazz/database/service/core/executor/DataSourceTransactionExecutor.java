package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface DataSourceTransactionExecutor {

    void writeTransactional(String query, Consumer<SqlTransaction> statement);

    Future<Void> writeTransactionalAsync(String query, Consumer<SqlTransaction> statement);

    void writeTransactional(String query, Consumer<PreparedStatementWrapper> statement, Consumer<SqlTransaction> transaction);

    Future<Void> writeTransactionalAsync(String query, Consumer<PreparedStatementWrapper> statement, Consumer<SqlTransaction> transaction);

}