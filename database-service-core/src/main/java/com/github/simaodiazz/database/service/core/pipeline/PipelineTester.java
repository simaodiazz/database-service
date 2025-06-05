package com.github.simaodiazz.database.service.core.pipeline;

public interface PipelineTester {

    /**
     * Phase used to set up the pipeline
     * @return
     */
    PipelinePhase setup();

    /**
     * Phase used to clean the pipeline
     * @return
     */
    PipelinePhase clean();

}
