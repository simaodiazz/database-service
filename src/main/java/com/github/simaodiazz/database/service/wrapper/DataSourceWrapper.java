package com.github.simaodiazz.database.service.wrapper;

import java.sql.Connection;

public interface DataSourceWrapper extends AutoCloseable {

    Connection getConnection();

    @Override
    void close();

}