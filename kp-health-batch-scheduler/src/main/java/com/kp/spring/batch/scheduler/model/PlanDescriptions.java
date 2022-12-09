package com.kp.spring.batch.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "plan_descriptions")
public class PlanDescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "plan_id")
    private String planId;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "coverage_type")
    private String coverageType;

    @Column(name = "estimated_premium")
    private String estimatedPremium;

    @Column(name = "annual_deductible_individual")
    private String annualDeductibleIndividual;

    @Column(name = "annual_deductible_family")
    private String annualDeductibleFamily;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getEstimatedPremium() {
        return estimatedPremium;
    }

    public void setEstimatedPremium(String estimatedPremium) {
        this.estimatedPremium = estimatedPremium;
    }

    public String getAnnualDeductibleIndividual() {
        return annualDeductibleIndividual;
    }

    public void setAnnualDeductibleIndividual(String annualDeductibleIndividual) {
        this.annualDeductibleIndividual = annualDeductibleIndividual;
    }

    public String getAnnualDeductibleFamily() {
        return annualDeductibleFamily;
    }

    public void setAnnualDeductibleFamily(String annualDeductibleFamily) {
        this.annualDeductibleFamily = annualDeductibleFamily;
    }
}