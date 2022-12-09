package com.kp.spring.batch.scheduler.controller;

import com.kp.spring.batch.scheduler.bean.AccumulatedDeductible;
import com.kp.spring.batch.scheduler.service.KPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestAPIController {

    private static final Logger LOG = LoggerFactory.getLogger(RestAPIController.class);


    @Autowired
    KPService kpService ;

    @RequestMapping(value="getIndividualAccumulatedDeductible", method = RequestMethod.GET)
    public @ResponseBody AccumulatedDeductible getIndividualAccumulatedDeductible(@RequestParam("policyId") String policyId ,
                                                                                  @RequestParam("policyHolderId") String policyHolderId){
        LOG.info("Policy Id::"+policyId+"::Policy Holder Id::"+policyHolderId);
        return kpService.getIndividualAccumulatedDeductible(policyId,policyHolderId);
    }

    @RequestMapping(value="getFamilyAccumulatedDeductible", method = RequestMethod.GET)
    public @ResponseBody AccumulatedDeductible getFamilyAccumulatedDeductible(@RequestParam("policyId") String policyId ){
        LOG.info("Policy Id::"+policyId);
        return kpService.getFamilyAccumulatedDeductible(policyId);
    }

}
