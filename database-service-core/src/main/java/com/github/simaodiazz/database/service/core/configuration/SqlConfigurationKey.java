package com.github.simaodiazz.database.service.core.configuration;

public interface SqlConfigurationKey<T> {

    String key();

    Class<T> type();

    T standard();

    final class PrimitiveSqlConfigurationKey<T> implements SqlConfigurationKey<T> {

        private final String key;
        private final Class<T> type;
        private T standard;

        public PrimitiveSqlConfigurationKey(String key, Class<T> type) {
            this.key = key;
            this.type = type;
        }

        public PrimitiveSqlConfigurationKey(String key, Class<T> type, T standard) {
            this.key = key;
            this.type = type;
            this.standard = standard;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public Class<T> type() {
            return type;
        }

        @Override
        public T standard() {
            return standard;
        }
    }
}
