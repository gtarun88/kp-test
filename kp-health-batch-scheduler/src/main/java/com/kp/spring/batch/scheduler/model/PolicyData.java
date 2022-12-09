package com.kp.spring.batch.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "processed_transaction_details")
public class PolicyData {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "policy_id")
	private String policyId;
	@Column(name = "policy_holder_id")
	private String policyHolderId ;

	@Column(name = "date_of_service")
	private String dateofService ;

	@Column(name = "coverage_main_category")
	private String coverageMainCategory ;

	@Column(name = "coverage_sub_category")
	private String coverageSubCategory ;

	@Column(name = "billed_amont")
	private String billedAmont ;
	@Column(name = "policy_holder_pays")
	private String policyHolderPays ;
	@Column(name = "plan_pays")
	private String planPays ;
	@Column(name = "rule_used")
	private String ruleUsed ;
	@Column(name = "individual_accumulated_deductible_servicedate")
	private String individualAccumulatedDeductibleServiceDate ;
	@Column(name = "family_accumulated_deductible_servicedate")
	private String familyAccumulatedDeductibleServiceDate ;
	@Column(name = "error_code")
	private String errorCode ;
	@Column(name = "error_message")
	private String errorMessage ;
	@Column(name = "processing_message")
	private String processingMessage ;

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

	public String getDateofService() {
		return dateofService;
	}

	public void setDateofService(String dateofService) {
		this.dateofService = dateofService;
	}

	public String getCoverageMainCategory() {
		return coverageMainCategory;
	}

	public void setCoverageMainCategory(String coverageMainCategory) {
		this.coverageMainCategory = coverageMainCategory;
	}

	public String getCoverageSubCategory() {
		return coverageSubCategory;
	}

	public void setCoverageSubCategory(String coverageSubCategory) {
		this.coverageSubCategory = coverageSubCategory;
	}

	public String getBilledAmont() {
		return billedAmont;
	}

	public void setBilledAmont(String billedAmont) {
		this.billedAmont = billedAmont;
	}

	public String getPolicyHolderPays() {
		return policyHolderPays;
	}

	public void setPolicyHolderPays(String policyHolderPays) {
		this.policyHolderPays = policyHolderPays;
	}

	public String getPlanPays() {
		return planPays;
	}

	public void setPlanPays(String planPays) {
		this.planPays = planPays;
	}

	public String getRuleUsed() {
		return ruleUsed;
	}

	public void setRuleUsed(String ruleUsed) {
		this.ruleUsed = ruleUsed;
	}

	public String getIndividualAccumulatedDeductibleServiceDate() {
		return individualAccumulatedDeductibleServiceDate;
	}

	public void setIndividualAccumulatedDeductibleServiceDate(String individualAccumulatedDeductibleServiceDate) {
		this.individualAccumulatedDeductibleServiceDate = individualAccumulatedDeductibleServiceDate;
	}

	public String getFamilyAccumulatedDeductibleServiceDate() {
		return familyAccumulatedDeductibleServiceDate;
	}

	public void setFamilyAccumulatedDeductibleServiceDate(String familyAccumulatedDeductibleServiceDate) {
		this.familyAccumulatedDeductibleServiceDate = familyAccumulatedDeductibleServiceDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getProcessingMessage() {
		return processingMessage;
	}

	public void setProcessingMessage(String processingMessage) {
		this.processingMessage = processingMessage;
	}
}
