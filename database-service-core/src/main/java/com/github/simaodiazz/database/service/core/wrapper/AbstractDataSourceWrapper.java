package com.github.simaodiazz.database.service.core.wrapper;

import com.github.simaodiazz.database.service.core.manager.SqlConnectionThreadLocalContextManager;
import com.github.simaodiazz.database.service.core.manager.SqlConnectionThreadLocalContextStrategy;
import com.github.simaodiazz.database.service.core.wrapper.connection.DefaultSqlConnection;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public abstract class AbstractDataSourceWrapper<S extends DataSource>
		implements DataSourceWrapper {

	protected S s;

	public AbstractDataSourceWrapper() {}

	public AbstractDataSourceWrapper(S s) {
		this.s = s;
	}

	@Override
	public SqlConnection getConnection(final boolean transactional) {
		try {
			SqlConnection sqlConnection = SqlConnectionThreadLocalContextManager.get().getConnection();
			if (sqlConnection == null) {
				final Connection connection = s.getConnection();
				sqlConnection = new DefaultSqlConnection(connection);
				SqlConnectionThreadLocalContextManager.set(sqlConnection,
                        transactional ?
                            SqlConnectionThreadLocalContextStrategy.TRANSACTION :
                            SqlConnectionThreadLocalContextStrategy.NORMAL);
			}
			return sqlConnection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
