package com.github.simaodiazz.database.service.core.wrapper.connection;

import com.github.simaodiazz.database.service.core.manager.SqlConnectionThreadLocalContextManager;
import com.github.simaodiazz.database.service.core.transaction.DefaultSqlTransaction;
import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.DefaultPreparedStatementWrapper;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractSqlConnection implements SqlConnection {

	protected final Connection connection;

	public AbstractSqlConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void addModifier(SqlConnectionModifier modifier) {
		modifier.apply(connection);
	}

	@Override
	public PreparedStatementWrapper prepareStatement(String sql) {
		try {
			final PreparedStatement statement = connection.prepareStatement(sql);
			return new DefaultPreparedStatementWrapper(statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SqlTransaction createTransaction() {
		SqlConnectionThreadLocalContextManager.set(this);
		return new DefaultSqlTransaction(this);
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
