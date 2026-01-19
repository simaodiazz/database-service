package com.github.simaodiazz.database;

import java.sql.ResultSet;

public final class ResultCursor implements AutoCloseable {

	private final ResultSet resultSet;

	public ResultCursor(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public <T> T get(int columnIndex, Class<T> type) {
		try {
			return resultSet.getObject(columnIndex, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T get(String columnName, Class<T> type) {
		try {
			return resultSet.getObject(columnName, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean hasNext() {
		try {
			return resultSet.next();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    public <T> T withAdapter(RowAdapter<T> adapter) {
        return adapter.adapt(this);
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
