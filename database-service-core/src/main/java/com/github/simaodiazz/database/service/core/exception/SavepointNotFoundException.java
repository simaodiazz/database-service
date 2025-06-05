package com.github.simaodiazz.database.service.core.exception;

import java.sql.SQLException;

public final class SavepointNotFoundException extends SQLException {

    public SavepointNotFoundException() {
        super("Savepoint not found");
    }

    public SavepointNotFoundException(String message) {
        super(message);
    }
}
