package com.github.simaodiazz.database.service.core.wrapper;

import com.github.simaodiazz.database.service.core.wrapper.connection.SqlConnection;

import java.sql.SQLException;

public interface DataSourceWrapper extends AutoCloseable {

    SqlConnection getConnection() throws SQLException;

    @Override
    void close();

}
