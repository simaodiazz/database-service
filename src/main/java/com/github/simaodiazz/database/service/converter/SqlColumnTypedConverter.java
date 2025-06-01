package com.github.simaodiazz.database.service.converter;

public abstract class SqlColumnTypedConverter<X, Y> implements SqlColumnConverter<X, Y> {

    protected final Class<X> type;

    public SqlColumnTypedConverter(Class<X> type) {
        this.type = type;
    }

    public Class<X> getType() {
        return type;
    }

    public boolean isInstance(Object value) {
        return type.isInstance(value);
    }

    public X cast(Object value) {
        final boolean isInstance = isInstance(value);
        if (isInstance)
            return type.cast(value);
        else throw new ClassCastException("Cannot cast " + value.getClass() + " to " + type);
    }
}
