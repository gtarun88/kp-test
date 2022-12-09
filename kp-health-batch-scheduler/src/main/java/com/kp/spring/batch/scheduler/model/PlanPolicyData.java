package com.kp.spring.batch.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "policy_data")
public class PlanPolicyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "policy_holder_id")
    private String policyHolderId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "plan_id")
    private String PlanId;

    @Column(name = "coverage_start_date")
    private String coverageStartDate;

    @Column(name = "coverage_end_date")
    private String coverageEndDate;

    @Column(name = "individual_accumulated_deductible_for_year")
    private Double individualAccumulatedDeductibleForYear;

    @Column(name = "family_accumulated_deductible_for_year")
    private Double familyAccumulatedDeductibleForYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlanId() {
        return PlanId;
    }

    public void setPlanId(String planId) {
        PlanId = planId;
    }

    public String getCoverageStartDate() {
        return coverageStartDate;
    }

    public void setCoverageStartDate(String coverageStartDate) {
        this.coverageStartDate = coverageStartDate;
    }

    public String getCoverageEndDate() {
        return coverageEndDate;
    }

    public void setCoverageEndDate(String coverageEndDate) {
        this.coverageEndDate = coverageEndDate;
    }

    public Double getIndividualAccumulatedDeductibleForYear() {
        return individualAccumulatedDeductibleForYear;
    }

    public void setIndividualAccumulatedDeductibleForYear(Double individualAccumulatedDeductibleForYear) {
        this.individualAccumulatedDeductibleForYear = individualAccumulatedDeductibleForYear;
    }

    public Double getFamilyAccumulatedDeductibleForYear() {
        return familyAccumulatedDeductibleForYear;
    }

    public void setFamilyAccumulatedDeductibleForYear(Double familyAccumulatedDeductibleForYear) {
        this.familyAccumulatedDeductibleForYear = familyAccumulatedDeductibleForYear;
    }
}