package com.github.simaodiazz.database;

@FunctionalInterface
public interface RowAdapter<T> {

	T adapt(ResultCursor resultCursor);

}
