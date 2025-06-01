package com.github.simaodiazz.database.service.converter;

import java.util.UUID;

public final class UuidSqlColumnConverter implements SqlColumnConverter<UUID, String> {

    @Override
    public String convertToColumn(UUID value) {
        return value.toString();
    }

    @Override
    public UUID convertToObject(String value) {
        return UUID.fromString(value);
    }
}
