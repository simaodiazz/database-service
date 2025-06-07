package com.github.simaodiazz.database.service.core.wrapper.connection;

import com.github.simaodiazz.database.service.core.transaction.SqlTransaction;
import com.github.simaodiazz.database.service.core.wrapper.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnection extends AutoCloseable {

	Connection getConnection();

	void addModifier(SqlConnectionModifier modifier);

	PreparedStatementWrapper prepareStatement(String sql);

	SqlTransaction createTransaction();

	@Override
	void close();
}
