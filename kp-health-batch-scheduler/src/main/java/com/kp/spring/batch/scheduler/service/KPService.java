package com.kp.spring.batch.scheduler.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kp.spring.batch.scheduler.bean.AccumulatedDeductible;
import com.kp.spring.batch.scheduler.model.PlanCoverage;
import com.kp.spring.batch.scheduler.model.PlanPolicyData;
import com.kp.spring.batch.scheduler.model.PolicyData;
import com.kp.spring.batch.scheduler.repository.PlanCoverageRepository;
import com.kp.spring.batch.scheduler.repository.PlanDescriptionsRepository;
import com.kp.spring.batch.scheduler.repository.PlanPolicyDataRepository;
import com.kp.spring.batch.scheduler.repository.ProcessedTransactionRepository;
import com.kp.spring.batch.scheduler.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPService {
    private static final String SEPARATOR = "::";
    @Autowired
    PlanDescriptionsRepository planDescriptionsRepository;

    @Autowired
    PlanCoverageRepository planCoverageRepository;

    @Autowired
    PlanPolicyDataRepository planPolicyDataRepository;

    @Autowired
    ProcessedTransactionRepository processedTransactionRepository;

    public List<String> getPlanIdList() {
        List<String> planIdList = new ArrayList<>();
        planDescriptionsRepository.findAll().forEach(planDescriptions -> planIdList.add(planDescriptions.getPlanId()));
        return planIdList;
    }

    public List<String> getPolicyIdList() {
        List<String> policyIdList = new ArrayList<>();
        planPolicyDataRepository.findAll().forEach(planPolicyData -> policyIdList.add(planPolicyData.getPolicyId()));
        return policyIdList;
    }

    public List<String> getPolicyHolderIdList() {
        List<String> policyHolderIdList = new ArrayList<>();
        planPolicyDataRepository.findAll().forEach(planPolicyData -> policyHolderIdList.add(planPolicyData.getPolicyHolderId()));
        return policyHolderIdList;
    }

    public Map<String, Object> getPlanDeductibleMap() {
        Map<String, Object> planDeductibleMap = new LinkedHashMap<>();
        planDescriptionsRepository.findAll().forEach(planDescriptions ->
                planDeductibleMap.put(planDescriptions.getPlanId(), planDescriptions.getAnnualDeductibleIndividual()
                        + SEPARATOR + planDescriptions.getAnnualDeductibleFamily())
        );
        return planDeductibleMap;
    }

    public Map<String, String> getPolicyPlanRuleMapData() {
        Map<String, String> policyPlanRuleMapData = new LinkedHashMap<>();
        planCoverageRepository.findAll().forEach(planCoverage -> setPlanPolicyData(planCoverage, policyPlanRuleMapData));
        return policyPlanRuleMapData;
    }


    public Map<String, Object> getPolicyHolderDetailsMapData() {
        Map<String, Object> policyHolderPlanMapData = new LinkedHashMap<>();
        planPolicyDataRepository.findAll().forEach(planPolicyData ->
                policyHolderPlanMapData.put(planPolicyData.getPolicyId() + SEPARATOR + planPolicyData.getPolicyHolderId()
                        , planPolicyData.getPlanId() + SEPARATOR + planPolicyData.getIndividualAccumulatedDeductibleForYear()
                                + SEPARATOR + planPolicyData.getFamilyAccumulatedDeductibleForYear())
        );
        return policyHolderPlanMapData;
    }

    public void setPlanPolicyData(PlanCoverage planCoverage, Map<String, String> policyPlanRuleMapData) {
        policyPlanRuleMapData.put(planCoverage.getMainCategory() + SEPARATOR + planCoverage.getSubCategory() + SEPARATOR + "P001", planCoverage.getPlan001());
        policyPlanRuleMapData.put(planCoverage.getMainCategory() + SEPARATOR + planCoverage.getSubCategory() + SEPARATOR + "P002", planCoverage.getPlan002());
        policyPlanRuleMapData.put(planCoverage.getMainCategory() + SEPARATOR + planCoverage.getSubCategory() + "P003", planCoverage.getPlan003());
    }

    public void saveProcessedTransactionDetails(PolicyData policyData) {
        processedTransactionRepository.save(policyData);
    }

    public AccumulatedDeductible getIndividualAccumulatedDeductible(String policyId, String policyHolderId) {
        AccumulatedDeductible accumulatedDeductible = new AccumulatedDeductible();
        Double individualAccumulatedDeductibleForYear = 0.0 ;
        if (!getPolicyIdList().contains(policyId)) {
            accumulatedDeductible.setSuccess(false);
            accumulatedDeductible.setErrorCode(ErrorCode.POLICY_ID_NON_EXISTENCE.getCode());
            accumulatedDeductible.setErrorMessage(ErrorCode.POLICY_ID_NON_EXISTENCE.getDescription());
            return accumulatedDeductible ;
        }
        if (!getPolicyHolderIdList().contains(policyHolderId)) {
            accumulatedDeductible.setSuccess(false);
            accumulatedDeductible.setErrorCode(ErrorCode.POLICY_HOLDER_NON_EXISTENCE.getCode());
            accumulatedDeductible.setErrorMessage(ErrorCode.POLICY_HOLDER_NON_EXISTENCE.getDescription());
            return accumulatedDeductible ;
        }
        PlanPolicyData planPolicyData = planPolicyDataRepository.findByPolicyIdAndPolicyHolderId(policyId,policyHolderId);
        if(null!=planPolicyData){
            individualAccumulatedDeductibleForYear = planPolicyData.getIndividualAccumulatedDeductibleForYear();
            accumulatedDeductible.setSuccess(true);
        }
        accumulatedDeductible.setAmount(String.format("%.2f",individualAccumulatedDeductibleForYear));
        return accumulatedDeductible ;
    }
    public AccumulatedDeductible getFamilyAccumulatedDeductible(String policyId) {
        AccumulatedDeductible accumulatedDeductible = new AccumulatedDeductible();
        if (!getPolicyIdList().contains(policyId)) {
            accumulatedDeductible.setSuccess(false);
            accumulatedDeductible.setErrorCode(ErrorCode.POLICY_ID_NON_EXISTENCE.getCode());
            accumulatedDeductible.setErrorMessage(ErrorCode.POLICY_ID_NON_EXISTENCE.getDescription());
            return accumulatedDeductible ;
        }
        List<PlanPolicyData> planPolicyDataList = planPolicyDataRepository.findByPolicyId(policyId);
        Double individualfamilyDeductibleForYear = 0.0;
        if(!planPolicyDataList.isEmpty()){
            for(PlanPolicyData planPolicyData: planPolicyDataList){
                individualfamilyDeductibleForYear += planPolicyData.getFamilyAccumulatedDeductibleForYear();
            }
            accumulatedDeductible.setSuccess(true);
        }
        accumulatedDeductible.setAmount(String.format("%.2f",individualfamilyDeductibleForYear));
        return accumulatedDeductible ;
    }

}
