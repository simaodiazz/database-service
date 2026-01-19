package com.github.simaodiazz.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Statement implements AutoCloseable {

	private final PreparedStatement statement;

	public Statement(PreparedStatement statement) {
		this.statement = statement;
	}

	public void execute() {
		try {
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ResultCursor query() {
		try {
			final ResultSet resultSet = statement.executeQuery();
			return new ResultCursor(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public <T> void setParameter(int parameterIndex, T value) {
		try {
			statement.setObject(parameterIndex, value);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addBatch() {
		try {
			statement.addBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

    @Override
	public void close() {
		try {
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
