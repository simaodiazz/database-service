package com.github.simaodiazz.database.service.core.wrapper;

import com.github.simaodiazz.database.service.core.converter.SqlColumnTypedConverter;
import java.sql.ResultSet;

public abstract class AbstractResultSetWrapper implements ResultSetWrapper {

	protected final ResultSet resultSet;

	public AbstractResultSetWrapper(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public <T> T get(int columnIndex, Class<T> type) {
		try {
			return resultSet.getObject(columnIndex, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <ObjectValue, ColumnValue> ObjectValue get(
			int columnIndex, SqlColumnTypedConverter<ObjectValue, ColumnValue> converter) {
		try {
			final ColumnValue value = resultSet.getObject(columnIndex, converter.getColumnValueClass());
			return converter.convertToObject(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T get(String columnName, Class<T> type) {
		try {
			return resultSet.getObject(columnName, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <ObjectValue, ColumnValue> ObjectValue get(
			String columnName, SqlColumnTypedConverter<ObjectValue, ColumnValue> converter) {
		try {
			final ColumnValue value = resultSet.getObject(columnName, converter.getColumnValueClass());
			return converter.convertToObject(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		try {
			return resultSet.next();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		try {
			resultSet.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
