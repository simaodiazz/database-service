package com.github.simaodiazz.database.service.core.converter;

import java.util.UUID;

public final class UuidSqlColumnConverter implements SqlColumnConverter<UUID, String> {

    @Override
    public String convertToColumn(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID convertToObject(String s) {
        return UUID.fromString(s);
    }
}
