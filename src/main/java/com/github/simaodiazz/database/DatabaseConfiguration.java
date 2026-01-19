package com.github.simaodiazz.database;

import lombok.Builder;

@Builder
public final class DatabaseConfiguration {

    @Builder.Default
    String url = "jdbc://localhost:3306/db";

    @Builder.Default
    String username = "root";

    @Builder.Default
    String password = "admin";

    @Builder.Default
    int maxPoolSize = Runtime.getRuntime().availableProcessors();

    @Builder.Default
    int connectionTimeout = 15000;

}
