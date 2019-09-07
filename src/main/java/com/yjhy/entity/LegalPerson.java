package com.yjhy.entity;

public class LegalPerson{

    private Integer id;

    private Integer companyId;

    private String lName;

    private String lIdcard;

    private Double lAllocation;

    private String lType;

    private String lJob;

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName == null ? null : lName.trim();
    }

    public String getlIdcard() {
        return lIdcard;
    }

    public void setlIdcard(String lIdcard) {
        this.lIdcard = lIdcard == null ? null : lIdcard.trim();
    }

    public Double getlAllocation() {
        return lAllocation;
    }

    public void setlAllocation(Double lAllocation) {
        this.lAllocation = lAllocation;
    }

    public String getlType() {
        return lType;
    }

    public void setlType(String lType) {
        this.lType = lType == null ? null : lType.trim();
    }

    public String getlJob() {
        return lJob;
    }

    public void setlJob(String lJob) {
        this.lJob = lJob == null ? null : lJob.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}