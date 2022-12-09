package com.kp.spring.batch.scheduler;

import com.kp.spring.batch.scheduler.step.PolicyJobProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KPHealthBatchSchedulerJobsApp implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(KPHealthBatchSchedulerJobsApp.class);
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job processTransaction;

	public static void main(String[] args) {
		SpringApplication.run(KPHealthBatchSchedulerJobsApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String inputTransactionFile = "";
		String outputTransactionFile = "";
		if(args.length==3){
			inputTransactionFile = args[1];
			outputTransactionFile = args[2];
		}
		else if(args.length==5){
			inputTransactionFile = args[3];
			outputTransactionFile = args[4];
		}

		LOG.info("inputTransactionFile::"+inputTransactionFile+" ::outputTransactionFile::"+outputTransactionFile);
		if(!inputTransactionFile.isEmpty()){
			JobParameters params = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis()))
					.addString("inputTransactionFile", inputTransactionFile)
					.addString("outputTransactionFile", outputTransactionFile)
					.toJobParameters();
			jobLauncher.run(processTransaction, params);
		}
	}
}
