package com.kp.spring.batch.scheduler.step;

import com.kp.spring.batch.scheduler.model.PolicyData;
import com.kp.spring.batch.scheduler.service.KPService;
import com.kp.spring.batch.scheduler.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class PolicyJobProcessor implements ItemProcessor<PolicyData, PolicyData> {
	private static final Logger LOG = LoggerFactory.getLogger(PolicyJobProcessor.class);

	@Autowired
	KPService kpService ;
	public PolicyJobProcessor(){

	}

	@Override
	public PolicyData process(PolicyData policyData) {
		LOG.info("PolicyJobProcessor process Policy: {}", policyData);
		PolicyData policyData1 = new ExcelUtil().getProcessUploadedData(policyData,kpService.getPlanDeductibleMap(),
				kpService.getPolicyHolderIdList(),kpService.getPolicyPlanRuleMapData(),kpService.getPolicyHolderDetailsMapData()
				,kpService.getPolicyIdList());
		if(null!=policyData1){
			kpService.saveProcessedTransactionDetails(policyData1);
		}
		return policyData1;
	}

}
