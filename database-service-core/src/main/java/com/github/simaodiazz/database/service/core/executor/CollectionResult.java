package com.github.simaodiazz.database.service.core.executor;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class CollectionResult<T> {

	private final List<T> list;

	public CollectionResult(List<T> list) {
		this.list = list;
	}

	public List<T> toList() {
		return list;
	}

	public List<T> toUnmodifiableList() {
		return Collections.unmodifiableList(list);
	}

	public Set<T> toSet() {
		return new HashSet<>(list);
	}

	public Set<T> toUnmodifiableSet() {
		final Set<T> set = this.toSet();
		return Collections.unmodifiableSet(set);
	}

	public Stream<T> toStream() {
		return list.stream();
	}
}
