package com.yjhy.entity;

public class Pic {
    private Integer picid;

    private String pictype;

    private String picsrc;

    private Integer picsort;

    private Integer cCompanyid;

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public String getPictype() {
        return pictype;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype == null ? null : pictype.trim();
    }

    public String getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(String picsrc) {
        this.picsrc = picsrc == null ? null : picsrc.trim();
    }

    public Integer getPicsort() {
        return picsort;
    }

    public void setPicsort(Integer picsort) {
        this.picsort = picsort;
    }

    public Integer getcCompanyid() {
        return cCompanyid;
    }

    public void setcCompanyid(Integer cCompanyid) {
        this.cCompanyid = cCompanyid;
    }
}