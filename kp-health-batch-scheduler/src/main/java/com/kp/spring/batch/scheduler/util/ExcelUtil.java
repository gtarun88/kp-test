package com.kp.spring.batch.scheduler.util;

import com.kp.spring.batch.scheduler.model.PolicyData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.kp.spring.batch.scheduler.util.ProcessingMessageUtil.*;

public class ExcelUtil {
    private static final Map<String, Object> individualPolicyHolderPlanCalulationMapData = new LinkedHashMap<>();
    private static final Map<String, Object> familyPolicyHolderPlanCalulationMapData = new LinkedHashMap<>();

    private static final String SEPARATOR = "::";

    public  PolicyData  getProcessUploadedData(PolicyData policyInputData,Map<String, Object> planDeductibleMap ,
        List<String> policyHolderIdList,Map<String, String> policyPlanRuleMapData
            ,Map<String, Object> policyHolderPlanMapData ,List<String> policyIdList) {
        String planId = "";
        String policyId;
        String policyholderId;
        String individualDeductible = null;
        String familyDeductible = null;
        String planRuleUsed;
        planRuleUsed = "";
        String  errorCode = "";
        String  errorCodeDescription = "";
        Double billedAmount;
        Double inputPlanIndividualDeductibleAmount = 0.00 ;
        Double inputPlanFamilyDeductibleAmount = 0.00 ;
        Double policyHolderPays = 0.00 ;
        Double planPays = 0.00;
        Double totalPlanIndividualAmount = 0.00 ;
        Double totalPlanFamilyAmount = 0.00 ;
        int rulePercentage = 1 ;
        String processingRule = PLAN_DO_NOT_PAYS;
        boolean planMetFlag = false ;
        policyId = policyInputData.getPolicyId();

        if (!policyIdList.contains(policyId)) {
            errorCode = ErrorCode.POLICY_ID_NON_EXISTENCE.getCode();
            errorCodeDescription = ErrorCode.POLICY_ID_NON_EXISTENCE.getDescription();
        }
        policyholderId = policyInputData.getPolicyHolderId();
        if (!policyHolderIdList.contains(policyholderId)) {
            errorCode = ErrorCode.POLICY_HOLDER_NON_EXISTENCE.getCode();
            errorCodeDescription = ErrorCode.POLICY_HOLDER_NON_EXISTENCE.getDescription();
        }
        if (errorCode.isEmpty()) {
            String mapKey = policyId + SEPARATOR + policyholderId;
            if (policyHolderPlanMapData.containsKey(mapKey)) {
                String planData = policyHolderPlanMapData.get(mapKey).toString();
                String[] dataArray = planData.split(SEPARATOR);
                if (dataArray.length == 3) {
                    planId = dataArray[0];
                    individualDeductible = dataArray[1];
                    familyDeductible = dataArray[2];
                }
                if (!planId.isEmpty() && !policyInputData.getCoverageMainCategory().isEmpty() && !policyInputData.getCoverageSubCategory().isEmpty()) {
                    //System.out.println("planId::::"+planId);
                    String categoryKey = policyInputData.getCoverageMainCategory() + SEPARATOR + policyInputData.getCoverageSubCategory() + SEPARATOR + planId;
                    if (policyPlanRuleMapData.containsKey(categoryKey)) {
                        planRuleUsed = policyPlanRuleMapData.get(categoryKey);
                        //System.out.println("planRuleUsed::"+planRuleUsed);
                    }
                }
            }
            String key = policyId+ SEPARATOR +policyholderId ;
            if(individualPolicyHolderPlanCalulationMapData.containsKey(key)){
                inputPlanIndividualDeductibleAmount = Double.parseDouble(individualPolicyHolderPlanCalulationMapData.get(key).toString());
            }
            if(familyPolicyHolderPlanCalulationMapData.containsKey(policyId)){
                inputPlanFamilyDeductibleAmount = Double.parseDouble(familyPolicyHolderPlanCalulationMapData.get(policyId).toString());
            }
            billedAmount = getFormattedDoubleValue(policyInputData.getBilledAmont()) ;

            policyHolderPays = 0.00 ;
            planPays = 0.00;

            if(planDeductibleMap.containsKey(planId)){
                String[] planAmount = planDeductibleMap.get(planId).toString().split(SEPARATOR);
                totalPlanIndividualAmount = getFormattedDoubleValue(planAmount[0]);
                totalPlanFamilyAmount = getFormattedDoubleValue(planAmount[1]);
            }
            int usedPlanRulePercentage = planRuleUsed.contains("%")?Integer.parseInt(planRuleUsed.substring(0, planRuleUsed.indexOf('%'))):0;
            int usedPlanRuleDollar = planRuleUsed.contains("$")?Integer.parseInt(planRuleUsed.substring(planRuleUsed.indexOf('$') + 1)):0;
            if(billedAmount+inputPlanIndividualDeductibleAmount <= totalPlanIndividualAmount){
                policyHolderPays = billedAmount ;
                planPays = 0.00;
                processingRule = PLAN_DO_NOT_PAYS;
            }
            else{
                if(!planRuleUsed.isEmpty()) {
                    if(planRuleUsed.contains("%")){
                        rulePercentage = usedPlanRulePercentage;
                        policyHolderPays = calculatePercentage(billedAmount,100-rulePercentage)  ;
                        planPays = calculatePercentage(billedAmount,rulePercentage) ;
                    }
                    else if(planRuleUsed.contains("$")){
                        rulePercentage = usedPlanRuleDollar;
                        policyHolderPays = billedAmount - rulePercentage;
                        planPays = Double.valueOf(rulePercentage);
                    }
                    processingRule = PLAN_PAYS_INDIVIDUAL.replace("0%",String.valueOf(rulePercentage)+"%");
                    planMetFlag  = true ;
                }
            }
            if(!planMetFlag){
                if(billedAmount+inputPlanFamilyDeductibleAmount <= totalPlanFamilyAmount){
                    policyHolderPays = billedAmount ;
                    planPays = 0.00;
                    processingRule = PLAN_DO_NOT_PAYS;
                }
                else{
                    if(!planRuleUsed.isEmpty()) {
                        if(planRuleUsed.contains("%")){
                            rulePercentage = usedPlanRulePercentage;
                            policyHolderPays = calculatePercentage(billedAmount,100-rulePercentage)  ;
                            planPays = calculatePercentage(billedAmount,rulePercentage) ;
                        }
                        else if(planRuleUsed.contains("$")){
                            rulePercentage = usedPlanRuleDollar;
                            policyHolderPays = billedAmount - rulePercentage;
                            planPays = Double.valueOf(rulePercentage);
                        }
                        processingRule = PLAN_PAYS_FAMILY.replace("0%",String.valueOf(rulePercentage)+"%");
                    }
                }
            }
            inputPlanIndividualDeductibleAmount += policyHolderPays;
            inputPlanFamilyDeductibleAmount += policyHolderPays ;
            individualPolicyHolderPlanCalulationMapData.put(key,inputPlanIndividualDeductibleAmount);
            familyPolicyHolderPlanCalulationMapData.put(policyId,inputPlanFamilyDeductibleAmount);
            policyInputData.setPolicyHolderPays("$  "+String.format("%.2f",policyHolderPays));
            policyInputData.setPlanPays(planPays.equals(0.0)?"0":"$  "+String.format("%.2f",planPays));
            policyInputData.setRuleUsed(planRuleUsed);
            policyInputData.setIndividualAccumulatedDeductibleServiceDate(inputPlanIndividualDeductibleAmount.equals(0.0)?"0":"$  "+String.format("%.2f",inputPlanIndividualDeductibleAmount));
            policyInputData.setFamilyAccumulatedDeductibleServiceDate(inputPlanFamilyDeductibleAmount.equals(0.0)?"0":"$  "+String.format("%.2f",inputPlanFamilyDeductibleAmount));
            policyInputData.setProcessingMessage(processingRule);
        }
        else{
            policyInputData.setErrorCode(errorCode);
            policyInputData.setErrorMessage(errorCodeDescription);
        }
        return policyInputData ;
    }
    private static Double calculatePercentage(Double amount,int rulePercentage){
        Double percentage = (amount *rulePercentage)/100 ;
        return percentage ;
    }
    private static Double getFormattedDoubleValue(Object obj){
            String data = obj.toString() ;
            if(!data.isEmpty()){
                data = data.replace("$","");
                data = data.replace(",","");
            }
            else{
                data = "0.00" ;
            }
        return  Double.parseDouble(data);
    }
}