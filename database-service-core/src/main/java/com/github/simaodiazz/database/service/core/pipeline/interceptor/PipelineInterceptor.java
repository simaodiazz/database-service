package com.github.simaodiazz.database.service.core.pipeline.interceptor;

import com.github.simaodiazz.database.service.core.pipeline.context.PipelineInterceptionContext;

@FunctionalInterface
public interface PipelineInterceptor<T> {

    PipelineInterceptingResult intercept(PipelineInterceptionContext<T> context);

}
