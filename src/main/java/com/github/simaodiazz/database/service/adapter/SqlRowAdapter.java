package com.github.simaodiazz.database.service.adapter;

import com.github.simaodiazz.database.service.wrapper.ResultSetWrapper;

import java.util.*;

public interface SqlRowAdapter<T> {

    Optional<T> optional(ResultSetWrapper resultSet);

    default List<T> list(ResultSetWrapper resultSet) {
        final List<T> list = new ArrayList<>();
        while (resultSet.next())
            optional(resultSet).ifPresent(list::add);
        return Collections.unmodifiableList(list);
    }

    default Set<T> set(ResultSetWrapper resultSet) {
        final Set<T> set = new HashSet<>();
        while (resultSet.next())
            optional(resultSet).ifPresent(set::add);
        return Collections.unmodifiableSet(set);
    }
}
