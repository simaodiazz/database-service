package com.github.simaodiazz.database.service.core.wrapper;

import com.github.simaodiazz.database.service.core.wrapper.connection.DefaultSqlConnection;
import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDataSourceWrapper<Source extends DataSource> implements DataSourceWrapper {

    protected Source source;

    public AbstractDataSourceWrapper() {
    }

    public AbstractDataSourceWrapper(Source source) {
        this.source = source;
    }

    @Override
    public SqlConnection getConnection() {
        try {
            final Connection connection = source.getConnection();
            return new DefaultSqlConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
