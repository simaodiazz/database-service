package com.github.simaodiazz.database.service.converter;

import com.google.gson.Gson;

public final class JsonSqlColumnConverter<T> extends SqlColumnTypedConverter<T, String> {

    private static final Gson GSON = new Gson();

    public JsonSqlColumnConverter(final Class<T> type) {
        super(type);
    }

    @Override
    public String convertToColumn(T value) {
        return GSON.toJson(value);
    }

    @Override
    public T convertToObject(String value) {
        return GSON.fromJson(value, type);
    }
}
