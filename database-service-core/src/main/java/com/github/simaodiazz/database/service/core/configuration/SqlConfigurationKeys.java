package com.github.simaodiazz.database.service.core.configuration;

public interface SqlConfigurationKeys {

	/** Settings for Data Source, is the connection pool normally */
	interface DataSource extends SqlConfigurationKeys {
		/** HikariDataSource is the default provider */
		SqlConfigurationKey<String> PROVIDER =
				new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
						"dataSourceProvider", String.class, "HikariDataSource");

		interface Hikari extends DataSource {
			SqlConfigurationKey<String> DRIVER =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"driver", String.class, "org.h2.Driver");
			SqlConfigurationKey<String> JDBC_URL =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"jdbcUrl", String.class, "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
			SqlConfigurationKey<String> USERNAME =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("username", String.class, "sa");
			SqlConfigurationKey<String> PASSWORD =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("password", String.class, "");

			SqlConfigurationKey<Integer> MAX_POOL_SIZE =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"maximumPoolSize", Integer.class, 10);
			SqlConfigurationKey<Integer> MIN_IDLE =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"minimumIdle", Integer.class, null);
			SqlConfigurationKey<Long> CONNECTION_TIMEOUT =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"connectionTimeout", Long.class, 30000L);
			SqlConfigurationKey<Long> IDLE_TIMEOUT =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"idleTimeout", Long.class, 600000L);
			SqlConfigurationKey<Long> MAX_LIFETIME =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"maxLifetime", Long.class, 1800000L);
			SqlConfigurationKey<Long> VALIDATION_TIMEOUT =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"validationTimeout", Long.class, 5000L);
			SqlConfigurationKey<Long> LEAK_DETECTION_THRESHOLD =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"leakDetectionThreshold", Long.class, 0L);

			SqlConfigurationKey<String> POOL_NAME =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("poolName", String.class, null);

			SqlConfigurationKey<String> CONNECTION_TEST_QUERY =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"connectionTestQuery", String.class, null);
			SqlConfigurationKey<Boolean> AUTO_COMMIT =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("autoCommit", Boolean.class, true);
			SqlConfigurationKey<Boolean> ISOLATE_INTERNAL_QUERIES =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"isolateInternalQueries", Boolean.class, false);
			SqlConfigurationKey<Boolean> ALLOW_POOL_SUSPENSION =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"allowPoolSuspension", Boolean.class, false);
			SqlConfigurationKey<Boolean> READ_ONLY =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("readOnly", Boolean.class, false);
			SqlConfigurationKey<String> CATALOG =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("catalog", String.class, null);
			SqlConfigurationKey<String> SCHEMA =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>("schema", String.class, null);
			SqlConfigurationKey<String> DATA_SOURCE_CLASS_NAME =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"dataSourceClassName", String.class, null);
		}
	}
	/** Settings for Executor Component, responsible for executing queries */
	interface Executor extends SqlConfigurationKeys {
		SqlConfigurationKey<Boolean> ENABLE_ASYNC =
				new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
						"executorEnableAsync", Boolean.class, false);

		interface Async extends Executor {
			SqlConfigurationKey<Integer> MAX_THREAD_POOL_SIZE =
					new SqlConfigurationKey.PrimitiveSqlConfigurationKey<>(
							"executorMaxThreadPoolSize", Integer.class, 10);
		}
	}
}
