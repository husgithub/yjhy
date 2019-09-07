package com.yjhy.entity;

import java.util.Date;

public class Taxation {
    private Integer id;

    private String tType;

    private String tName;

    private String tPhone;

    private String tAddress;

    private Date tChecktime;

    private String tAccountantphone;

    private String tVariety;

    private String tShenbao;

    private Integer tCompanyid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType == null ? null : tType.trim();
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone == null ? null : tPhone.trim();
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress == null ? null : tAddress.trim();
    }

    public Date gettChecktime() {
        return tChecktime;
    }

    public void settChecktime(Date tChecktime) {
        this.tChecktime = tChecktime;
    }

    public String gettAccountantphone() {
        return tAccountantphone;
    }

    public void settAccountantphone(String tAccountantphone) {
        this.tAccountantphone = tAccountantphone == null ? null : tAccountantphone.trim();
    }

    public String gettVariety() {
        return tVariety;
    }

    public void settVariety(String tVariety) {
        this.tVariety = tVariety == null ? null : tVariety.trim();
    }

    public String gettShenbao() {
        return tShenbao;
    }

    public void settShenbao(String tShenbao) {
        this.tShenbao = tShenbao == null ? null : tShenbao.trim();
    }

    public Integer gettCompanyid() {
        return tCompanyid;
    }

    public void settCompanyid(Integer tCompanyid) {
        this.tCompanyid = tCompanyid;
    }
}