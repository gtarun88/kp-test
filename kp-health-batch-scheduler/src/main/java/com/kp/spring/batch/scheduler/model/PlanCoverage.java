package com.kp.spring.batch.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "plan_coverage")
public class PlanCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "main_category")
    private String mainCategory;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "plan_001")
    private String plan001;

    @Column(name = "plan_002")
    private String plan002;

    @Column(name = "plan_003")
    private String plan003;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getPlan001() {
        return plan001;
    }

    public void setPlan001(String plan001) {
        this.plan001 = plan001;
    }

    public String getPlan002() {
        return plan002;
    }

    public void setPlan002(String plan002) {
        this.plan002 = plan002;
    }

    public String getPlan003() {
        return plan003;
    }

    public void setPlan003(String plan003) {
        this.plan003 = plan003;
    }
}