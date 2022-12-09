package com.kp.spring.batch.scheduler.step;

import com.kp.spring.batch.scheduler.model.PolicyData;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class PolicyJobFlatFileReader {
	public PolicyJobFlatFileReader(){
	}
	@Bean
	@StepScope
	public FlatFileItemReader<PolicyData> reader(String inputTransactionFile) {
		FlatFileItemReader<PolicyData> reader = new FlatFileItemReader<>();
		List<String> beanFieldHeaderList = getBeanFieldHeaderList();
		//Resource inputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/TestProject_New/Test/InputData/"+inputTransactionFile);
		Resource inputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/InputData/"+inputTransactionFile);
		if(!inputTransactionFile.isEmpty() && inputResource.isFile()) {
			reader.setResource(inputResource);
		}
		reader.setLinesToSkip(1);
		reader.setStrict(false);
		reader.setLineMapper(new DefaultLineMapper<PolicyData>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(beanFieldHeaderList.toArray(new String[0]));
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<PolicyData>() {
					{
						setTargetType(PolicyData.class);
					}
				});
			}
		});
		return reader;
	}
	private List<String> getBeanFieldHeaderList(){
		List<String> beanFieldHeaderList = new ArrayList<>();
		beanFieldHeaderList.add("PolicyId");
		beanFieldHeaderList.add("Policy holder Id");
		beanFieldHeaderList.add("Date of service");
		beanFieldHeaderList.add("Coverage Main Category");
		beanFieldHeaderList.add("Coverage Sub Category");
		beanFieldHeaderList.add("Billed Amont");
		return beanFieldHeaderList ;
	}
}
