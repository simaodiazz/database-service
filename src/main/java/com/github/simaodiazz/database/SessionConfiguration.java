package com.github.simaodiazz.database;

import lombok.Builder;

@Builder
public final class SessionConfiguration {

    boolean autoCommit;
    boolean readOnly;
    TransactionLevel transactionLevel;

}
