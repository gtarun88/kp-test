package com.kp.spring.batch.scheduler.step;

import com.kp.spring.batch.scheduler.model.PolicyData;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class PolicyJobFlatFileWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<PolicyData> writer(String outputTransactionFile)
    {
        List<String> fieldHeaderList  = getFieldHearerList();
        String columns = String.join(",", fieldHeaderList);
        //Resource outputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/TestProject_New/Test/ProcessedData/"+outputTransactionFile);
        Resource outputResource = new FileSystemResource(System.getProperty("user.home") + "/Documents/ProcessedData/"+outputTransactionFile);
        FlatFileItemWriter<PolicyData> writer = new FlatFileItemWriter<>();
        if(outputResource.isFile()) {
            writer.setResource(outputResource);
        }
        writer.setHeaderCallback(writer1 -> writer1.write(columns));
        writer.setLineAggregator(new DelimitedLineAggregator<PolicyData>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<PolicyData>() {
                    {
                        setNames(getHearerList().toArray(new String[0]));
                    }
                });
            }
        });
        return writer;
    }

    private List<String> getHearerList(){
        List<String> headerList = new ArrayList<>();
        headerList.add("policyId");
        headerList.add("policyHolderId");
        headerList.add("dateofService");
        headerList.add("coverageMainCategory");
        headerList.add("coverageSubCategory");
        headerList.add("billedAmont");
        headerList.add("policyHolderPays");
        headerList.add("planPays");
        headerList.add("ruleUsed");
        headerList.add("individualAccumulatedDeductibleServiceDate");
        headerList.add("familyAccumulatedDeductibleServiceDate");
        headerList.add("errorCode");
        headerList.add("errorMessage");
        headerList.add("processingMessage");
        return headerList ;
    }
    private List<String> getFieldHearerList() {
        List<String> fieldHeaderList = new ArrayList<>();
        fieldHeaderList.add("PolicyId");
        fieldHeaderList.add("Policy holder Id");
        fieldHeaderList.add("Date of service");
        fieldHeaderList.add("Coverage Main Category");
        fieldHeaderList.add("Coverage Sub Category");
        fieldHeaderList.add("Billed Amont");
        fieldHeaderList.add("Policy Holder pays");
        fieldHeaderList.add("Plan Pays");
        fieldHeaderList.add("Rule used");
        fieldHeaderList.add("Individual accumulated deductible as of service date");
        fieldHeaderList.add("Family accumulated deductible as of service date");
        fieldHeaderList.add("Error Code");
        fieldHeaderList.add("Error Message");
        fieldHeaderList.add("Processing message");
        return fieldHeaderList;
    }
}
