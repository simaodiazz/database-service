package com.github.simaodiazz.database;

import java.sql.Connection;

public enum TransactionLevel {

    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE),
    NONE(Connection.TRANSACTION_NONE);

    final int level;

    TransactionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static TransactionLevel fromId(int id) {
        for (TransactionLevel type : values()) {
            if (type.level == id) return type;
        }
        return NONE;
    }
}
