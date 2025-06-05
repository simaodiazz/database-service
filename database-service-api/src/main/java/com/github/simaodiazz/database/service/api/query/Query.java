package com.github.simaodiazz.database.service.api.query;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.converter.SqlColumnTypedConverter;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutor;
import com.github.simaodiazz.database.service.core.executor.DataSourceExecutorAware;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public final class Query<T> implements DataSourceExecutorAware {

    private DataSourceExecutor dataSourceExecutor;

    private String instruction;
    private final List<Object> parameters;

    private SqlRowAdapter<T> rowAdapter;

    public Query(DataSourceExecutor dataSourceExecutor) {
        this.dataSourceExecutor = dataSourceExecutor;
        this.parameters = new ArrayList<>();
    }

    public Query<T> instruction(String name) {
        this.instruction = name;
        return this;
    }

    public Query<T> parameter(Object parameter) {
        parameters.add(parameter);
        return this;
    }

    public <ColumnValue, ObjectValue> Query<T> parameterConverter(ColumnValue object, SqlColumnTypedConverter<ColumnValue, ObjectValue> converter) {
        final ObjectValue value = converter.convertToColumn(object);
        parameters.add(value);
        return this;
    }

    public Query<T> rowAdapter(SqlRowAdapter<T> rowAdapter) {
        Objects.requireNonNull(rowAdapter);
        this.rowAdapter = rowAdapter;
        return this;
    }

    public void write() {
        dataSourceExecutor.write(instruction, statement -> {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setParameter(i + 1, parameters.get(i));
            }
        });
    }

    public Optional<T> querySingle(boolean transactional) {
        if (transactional)
            return dataSourceExecutor.readTransactionalOptional(instruction, rowAdapter, statement -> {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setParameter(i + 1, parameters.get(i));
                }
            });
        return dataSourceExecutor.readOptional(instruction, rowAdapter, statement -> {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setParameter(i + 1, parameters.get(i));
            }
        });
    }

    public CompletableFuture<Optional<T>> querySingleAsync(boolean transactional) {
        if (transactional)
            return dataSourceExecutor.readTransactionalOptionalAsync(instruction, rowAdapter, statement -> {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setParameter(i + 1, parameters.get(i));
                }
            });
        return dataSourceExecutor.readOptionalAsync(instruction, rowAdapter, statement -> {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setParameter(i + 1, parameters.get(i));
            }
        });
    }

    public Optional<T> queryList(boolean transactional) {
        if (transactional)
            return dataSourceExecutor.readTransactionalOptional(instruction, rowAdapter, statement -> {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setParameter(i + 1, parameters.get(i));
                }
            });
        return dataSourceExecutor.readTransactionalOptional(instruction, rowAdapter, statement -> {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setParameter(i + 1, parameters.get(i));
            }
        });
    }

    public CompletableFuture<List<T>> queryListAsync(boolean transactional) {
        if (transactional)
            return dataSourceExecutor.readTransactionalAllAsync(instruction, rowAdapter, statement -> {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setParameter(i + 1, parameters.get(i));
                }
            });
        return dataSourceExecutor.readAllAsync(instruction, rowAdapter, statement -> {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setParameter(i + 1, parameters.get(i));
            }
        });
    }

    public void setDataSourceExecutor(DataSourceExecutor dataSourceExecutor) {
        this.dataSourceExecutor = dataSourceExecutor;
    }
}
