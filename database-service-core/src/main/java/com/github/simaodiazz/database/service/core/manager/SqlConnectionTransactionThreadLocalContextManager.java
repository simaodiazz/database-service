package com.github.simaodiazz.database.service.core.manager;

import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;

public final class SqlConnectionTransactionThreadLocalContextManager {

	private static final ThreadLocal<SqlConnection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

	public static void set(SqlConnection sqlConnection) {
		CONNECTION_THREAD_LOCAL.set(sqlConnection);
	}

	public static SqlConnection get() {
		return CONNECTION_THREAD_LOCAL.get();
	}

	public static void remove() {
		CONNECTION_THREAD_LOCAL.remove();
	}
}
