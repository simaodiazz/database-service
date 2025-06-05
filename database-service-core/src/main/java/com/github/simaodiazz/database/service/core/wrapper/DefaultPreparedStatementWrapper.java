package com.github.simaodiazz.database.service.core.wrapper;

import java.sql.PreparedStatement;

public final class DefaultPreparedStatementWrapper extends AbstractPreparedStatementWrapper {

    public DefaultPreparedStatementWrapper(PreparedStatement statement) {
        super(statement);
    }
}
