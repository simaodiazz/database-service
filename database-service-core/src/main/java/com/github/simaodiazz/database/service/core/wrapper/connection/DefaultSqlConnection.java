package com.github.simaodiazz.database.service.core.wrapper.connection;

import java.sql.Connection;

public final class DefaultSqlConnection extends AbstractSqlConnection {

    public DefaultSqlConnection(Connection connection) {
        super(connection);
    }
}
