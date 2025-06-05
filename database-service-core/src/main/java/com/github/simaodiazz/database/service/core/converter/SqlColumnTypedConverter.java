package com.github.simaodiazz.database.service.core.converter;

public abstract class SqlColumnTypedConverter<ObjectValue, ColumnValue> implements SqlColumnConverter<ObjectValue, ColumnValue> {

    protected final Class<ObjectValue> objectValueClass;
    protected final Class<ColumnValue> columnValueClass;

    public SqlColumnTypedConverter(Class<ObjectValue> objectValueClass, Class<ColumnValue> columnValueClass) {
        this.objectValueClass = objectValueClass;
        this.columnValueClass = columnValueClass;
    }

    public Class<ObjectValue> getObjectValueClass() {
        return objectValueClass;
    }

    public Class<ColumnValue> getColumnValueClass() {
        return columnValueClass;
    }
}
