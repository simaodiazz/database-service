package com.github.simaodiazz.database.service.core.executor;

import com.github.simaodiazz.database.service.core.adapter.SqlRowAdapter;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.DataSourceWrapper;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import com.github.simaodiazz.database.service.core.wrapper.ResultSetWrapper;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnectionModifier;

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
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                 ResultSetWrapper resultSet = statement.query()) {
                return adapter.single(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> readOptional(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                 ResultSetWrapper resultSet = statement.query()) {
                consumer.accept(statement);
                return adapter.single(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> readAll(String query, SqlRowAdapter<T> adapter) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                 ResultSetWrapper resultSet = statement.query()) {
                return adapter.many(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> readAll(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                 ResultSetWrapper resultSet = statement.query()) {
                consumer.accept(statement);
                return adapter.many(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTransactional(String query, Consumer<SqlTransaction> sqlTransactionConsumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query)) {
                    statement.execute();
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTransactional(String query, Consumer<PreparedStatementWrapper> statementWrapperConsumer, Consumer<SqlTransaction> sqlTransactionConsumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query)) {
                    statementWrapperConsumer.accept(statement);
                    statement.execute();
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> readTransactionalOptional(String query, SqlRowAdapter<T> adapter) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            connection.addModifier(SqlConnectionModifier.TRANSACTION_READ_COMMITED);
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                     ResultSetWrapper resultSet = statement.query()) {
                    final Optional<T> result = adapter.single(resultSet);
                    transaction.commit();
                    return result;
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> readTransactionalOptional(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            connection.addModifier(SqlConnectionModifier.TRANSACTION_READ_COMMITED);
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                     ResultSetWrapper resultSet = statement.query()) {
                    consumer.accept(statement);
                    final Optional<T> result = adapter.single(resultSet);
                    transaction.commit();
                    return result;
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> readTransactionalAll(String query, SqlRowAdapter<T> adapter) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            connection.addModifier(SqlConnectionModifier.TRANSACTION_READ_COMMITED);
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                     ResultSetWrapper resultSet = statement.query()) {
                    final List<T> result = adapter.many(resultSet);
                    transaction.commit();
                    return result;
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> readTransactionalAll(String query, SqlRowAdapter<T> adapter, Consumer<PreparedStatementWrapper> consumer) {
        try (SqlConnection connection = source.getConnection()) {
            connection.addModifier(SqlConnectionModifier.TRANSACTION);
            connection.addModifier(SqlConnectionModifier.TRANSACTION_READ_COMMITED);
            connection.addModifier(SqlConnectionModifier.READ_ONLY);
            try (SqlTransaction transaction = connection.createTransaction()) {
                try (PreparedStatementWrapper statement = connection.prepareStatement(query);
                     ResultSetWrapper resultSet = statement.query()) {
                    consumer.accept(statement);
                    final List<T> result = adapter.many(resultSet);
                    transaction.commit();
                    return result;
                } catch (SQLException e) {
                    transaction.rollback();
                    throw e;
                }
            }
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
