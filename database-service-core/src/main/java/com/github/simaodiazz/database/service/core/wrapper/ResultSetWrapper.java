package com.github.simaodiazz.database.service.core.wrapper;

import com.github.simaodiazz.database.service.core.converter.SqlColumnTypedConverter;

public interface ResultSetWrapper extends AutoCloseable {

	<T> T get(int columnIndex, Class<T> type);

	<ObjectValue, ColumnValue> ObjectValue get(
			int columnIndex, SqlColumnTypedConverter<ObjectValue, ColumnValue> converter);

	<T> T get(String columnName, Class<T> type);

	<ObjectValue, ColumnValue> ObjectValue get(
			String columnName, SqlColumnTypedConverter<ObjectValue, ColumnValue> converter);

	boolean hasNext();

	@Override
	void close();
}
