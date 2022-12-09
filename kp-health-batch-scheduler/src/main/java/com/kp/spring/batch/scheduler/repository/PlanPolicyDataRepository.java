package com.kp.spring.batch.scheduler.repository;

import com.kp.spring.batch.scheduler.model.PlanPolicyData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanPolicyDataRepository extends JpaRepository<PlanPolicyData, Long> {
    PlanPolicyData findByPolicyIdAndPolicyHolderId(String policyId, String policyHolderId);
    List<PlanPolicyData> findByPolicyId(String policyId);
}