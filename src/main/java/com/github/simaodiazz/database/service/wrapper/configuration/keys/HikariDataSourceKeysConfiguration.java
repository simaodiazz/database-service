package com.github.simaodiazz.database.service.wrapper.configuration.keys;

@FunctionalInterface
public interface HikariDataSourceKeysConfiguration<T> extends ConfigurationKeysInterface {

    String key();

    HikariDataSourceKeysConfiguration<String> DRIVER_CLASS_NAME = () -> "driverClassName";
    HikariDataSourceKeysConfiguration<String> JDBC_URL = () -> "jdbcUrl";
    HikariDataSourceKeysConfiguration<String> USERNAME = () -> "username";
    HikariDataSourceKeysConfiguration<String> PASSWORD = () -> "password";
    HikariDataSourceKeysConfiguration<Integer> MAX_POOL_SIZE = () -> "maximumPoolSize";
    HikariDataSourceKeysConfiguration<Integer> MIN_IDLE = () -> "minimumIdle";
    HikariDataSourceKeysConfiguration<Long> CONNECTION_TIMEOUT = () -> "connectionTimeout";
    HikariDataSourceKeysConfiguration<Long> IDLE_TIMEOUT = () -> "idleTimeout";
    HikariDataSourceKeysConfiguration<Long> MAX_LIFETIME = () -> "maxLifetime";
    HikariDataSourceKeysConfiguration<Long> LEAK_DETECTION_THRESHOLD = () -> "leakDetectionThreshold";
    HikariDataSourceKeysConfiguration<String> POOL_NAME = () -> "poolName";
    HikariDataSourceKeysConfiguration<String> DATA_SOURCE_CLASS_NAME = () -> "dataSourceClassName";
    HikariDataSourceKeysConfiguration<String> CONNECTION_TEST_QUERY = () -> "connectionTestQuery";
    HikariDataSourceKeysConfiguration<Boolean> AUTO_COMMIT = () -> "autoCommit";
    HikariDataSourceKeysConfiguration<Boolean> ISOLATE_INTERNAL_QUERIES = () -> "isolateInternalQueries";
    HikariDataSourceKeysConfiguration<Boolean> ALLOW_POOL_SUSPENSION = () -> "allowPoolSuspension";
    HikariDataSourceKeysConfiguration<Boolean> READ_ONLY = () -> "readOnly";
    HikariDataSourceKeysConfiguration<String> CATALOG = () -> "catalog";
    HikariDataSourceKeysConfiguration<String> TRANSACTION_ISOLATION = () -> "transactionIsolation";

}
