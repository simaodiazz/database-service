package com.github.simaodiazz.database.service.core.adapter;

import com.github.simaodiazz.database.service.core.wrapper.ResultSetWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SqlRowAdapter<T> {

	Optional<T> single(ResultSetWrapper resultSet);

	default List<T> many(ResultSetWrapper resultSet) {
		final List<T> list = new ArrayList<>();
		while (resultSet.hasNext()) single(resultSet).ifPresent(list::add);
		return list;
	}
}
