package com.github.simaodiazz.database.service.core.converter;

public interface SqlColumnConverter<ObjectValue, ColumnValue> {

    ColumnValue convertToColumn(ObjectValue value);

    ObjectValue convertToObject(ColumnValue value);

}
