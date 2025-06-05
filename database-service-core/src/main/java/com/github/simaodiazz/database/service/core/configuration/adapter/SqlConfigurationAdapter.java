package com.github.simaodiazz.database.service.core.configuration.adapter;

import com.github.simaodiazz.database.service.core.configuration.SqlConfiguration;

public interface SqlConfigurationAdapter<C> {

    SqlConfiguration adapt(C configuration);

}
