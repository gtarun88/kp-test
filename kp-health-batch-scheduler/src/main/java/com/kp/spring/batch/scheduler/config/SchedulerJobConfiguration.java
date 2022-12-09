
package com.kp.spring.batch.scheduler.config;

import com.kp.spring.batch.scheduler.listener.PolicyStepListener;
import com.kp.spring.batch.scheduler.model.PolicyData;
import com.kp.spring.batch.scheduler.listener.PolicyJobListener;
import com.kp.spring.batch.scheduler.listener.PolicyReadListener;
import com.kp.spring.batch.scheduler.listener.PolicyWriteListener;
import com.kp.spring.batch.scheduler.step.FileDeletingTasklet;
import com.kp.spring.batch.scheduler.step.PolicyJobFlatFileReader;
import com.kp.spring.batch.scheduler.step.PolicyJobFlatFileWriter;
import com.kp.spring.batch.scheduler.step.PolicyJobProcessor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class SchedulerJobConfiguration {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    private static final String OVERRIDDEN_BY_EXPRESSION = null;

    @Scheduled(cron = "0 0/30 * * * *")
    //@Scheduled(fixedRate = 10000)
    public void runProcessTransaction() {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("inputTransactionFile", "batchTransactionFile.csv")
                .addString("outputTransactionFile", "batchTransactionProcessedFile.csv")
                .toJobParameters();
        try {
            jobLauncher().run(processTransaction(), params);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Bean
    @StepScope
    FlatFileItemReader<PolicyData> policyJobFlatFileReader(@Value("#{jobParameters[inputTransactionFile]}")
                                                           String inputTransactionFile) {
        return new PolicyJobFlatFileReader().reader(inputTransactionFile);
    }

    @Bean
    public ItemProcessor<PolicyData, PolicyData> policyJobProcessor() {
        return new PolicyJobProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<PolicyData> policyJobFlatFileWriter(@Value("#{jobParameters[outputTransactionFile]}")
                                                                      String outputTransactionFile) {
        return new PolicyJobFlatFileWriter().writer(outputTransactionFile);
    }
    @Bean
    public ItemReadListener<PolicyData> policyReadListener() {
        return new PolicyReadListener<>();
    }

    @Bean
    public StepExecutionListener policyStepListener() {
        return new PolicyStepListener();
    }

    @Bean
    public ItemWriteListener<List<PolicyData>> policyWriteListener() {
        return new PolicyWriteListener<>();
    }

    @Bean
    public JobExecutionListener policyJobListener() {
        return new PolicyJobListener();
    }
    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("Step1").listener(policyReadListener())
                .listener(policyStepListener())
                .listener(policyWriteListener()).<PolicyData, PolicyData>chunk(1)
                .reader(policyJobFlatFileReader(OVERRIDDEN_BY_EXPRESSION))
                .processor(policyJobProcessor())
                .writer(policyJobFlatFileWriter(OVERRIDDEN_BY_EXPRESSION)).build();
    }
    @Bean
    public Step step2() {
        FileDeletingTasklet task = new FileDeletingTasklet();
        return stepBuilderFactory.get("step2")
                .tasklet(task)
                .build();
    }
    @Bean
    public Job processTransaction() {
        return this.jobBuilderFactory.get("processTransaction").listener(policyJobListener()).incrementer(new RunIdIncrementer())
                .start(step1()).next(step2()).build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(15);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(30);
        return taskExecutor;
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setTaskExecutor(taskExecutor());
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
