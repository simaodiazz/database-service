package com.github.simaodiazz.database.service.converter;

import com.github.simaodiazz.database.service.wrapper.PreparedStatementWrapper;

import java.util.UUID;

public final class UuidSqlColumnConverter implements SqlColumnConverter<UUID, String> {

    @Override
    public String convertToColumn(UUID value) {
        return value.toString();
    }

    @Override
    public String convertToColumn(UUID value, PreparedStatementWrapper wrapper) {
        return "";
    }

    @Override
    public UUID convertToObject(String value) {
        return UUID.fromString(value);
    }
}
