package com.kp.spring.batch.scheduler.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


import java.io.File;

public class FileDeletingTasklet implements Tasklet, InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(FileDeletingTasklet.class);

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String inputTransactionFile = (String) chunkContext.getStepContext().getJobParameters().get("inputTransactionFile");
        //Resource inputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/TestProject_New/Test/InputData/"+inputTransactionFile);
        Resource inputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/InputData/"+inputTransactionFile);
        if(!inputTransactionFile.isEmpty() && inputResource.isFile()){
            File file = inputResource.getFile();
            boolean deleted = file.delete();
            if (!deleted) {
                LOG.info("Could not delete file " + file.getPath());
            }
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() {

    }
}
