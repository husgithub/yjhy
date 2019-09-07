package com.yjhy.entity;

import java.util.Date;
import java.util.List;

public class Company {
    private Integer id;

    private String cName;

    private String cAddress;

    private String cContacts;

    private String cEmail;

    private String cSize;

    private Double cCapital;

    private String cType;

    private String cScope;

    private String cCusource;

    private String cReferrer;

    private String cOperator;

    private String cOperatorphone;

    private Date cRegtime;

    private String cRegcode;

    private Date bnStart;

    private Date bnEnd;

    private String cTaxationtype;

    private String cPublicaccount;

    private String cIsreported;

    private String cAccountantname;

    private String cIsjianz;

    private Date cCreatetime;

    private Date cModification;

    private String cStatus;

    private String cUpdate;

    private String cNotes;

    //附加属性
    private List<LegalPerson> legalPersons =null;

    private List<Pic> pics = null;

    private List<Taxation> taxations = null;

    private String cRegtimeStart = null;    //注册日期开始查询条件

    private String cRegtimeEnd = null;      //注册日期结束查询条件

    private Double totalMoney = null;

    private List<Earnings> earnings = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
    }

    public String getcContacts() {
        return cContacts;
    }

    public void setcContacts(String cContacts) {
        this.cContacts = cContacts == null ? null : cContacts.trim();
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail == null ? null : cEmail.trim();
    }

    public String getcSize() {
        return cSize;
    }

    public void setcSize(String cSize) {
        this.cSize = cSize == null ? null : cSize.trim();
    }

    public Double getcCapital() {
        return cCapital;
    }

    public void setcCapital(Double cCapital) {
        this.cCapital = cCapital;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
    }

    public String getcScope() {
        return cScope;
    }

    public void setcScope(String cScope) {
        this.cScope = cScope == null ? null : cScope.trim();
    }

    public String getcCusource() {
        return cCusource;
    }

    public void setcCusource(String cCusource) {
        this.cCusource = cCusource == null ? null : cCusource.trim();
    }

    public String getcReferrer() {
        return cReferrer;
    }

    public void setcReferrer(String cReferrer) {
        this.cReferrer = cReferrer == null ? null : cReferrer.trim();
    }

    public String getcOperator() {
        return cOperator;
    }

    public void setcOperator(String cOperator) {
        this.cOperator = cOperator == null ? null : cOperator.trim();
    }

    public String getcOperatorphone() {
        return cOperatorphone;
    }

    public void setcOperatorphone(String cOperatorphone) {
        this.cOperatorphone = cOperatorphone == null ? null : cOperatorphone.trim();
    }

    public Date getcRegtime() {
        return cRegtime;
    }

    public void setcRegtime(Date cRegtime) {
        this.cRegtime = cRegtime;
    }

    public String getcRegcode() {
        return cRegcode;
    }

    public void setcRegcode(String cRegcode) {
        this.cRegcode = cRegcode == null ? null : cRegcode.trim();
    }

    public Date getBnStart() {
        return bnStart;
    }

    public void setBnStart(Date bnStart) {
        this.bnStart = bnStart;
    }

    public Date getBnEnd() {
        return bnEnd;
    }

    public void setBnEnd(Date bnEnd) {
        this.bnEnd = bnEnd;
    }

    public String getcTaxationtype() {
        return cTaxationtype;
    }

    public void setcTaxationtype(String cTaxationtype) {
        this.cTaxationtype = cTaxationtype == null ? null : cTaxationtype.trim();
    }

    public String getcPublicaccount() {
        return cPublicaccount;
    }

    public void setcPublicaccount(String cPublicaccount) {
        this.cPublicaccount = cPublicaccount == null ? null : cPublicaccount.trim();
    }

    public String getcIsreported() {
        return cIsreported;
    }

    public void setcIsreported(String cIsreported) {
        this.cIsreported = cIsreported == null ? null : cIsreported.trim();
    }

    public String getcAccountantname() {
        return cAccountantname;
    }

    public void setcAccountantname(String cAccountantname) {
        this.cAccountantname = cAccountantname == null ? null : cAccountantname.trim();
    }

    public String getcIsjianz() {
        return cIsjianz;
    }

    public void setcIsjianz(String cIsjianz) {
        this.cIsjianz = cIsjianz == null ? null : cIsjianz.trim();
    }

    public Date getcCreatetime() {
        return cCreatetime;
    }

    public void setcCreatetime(Date cCreatetime) {
        this.cCreatetime = cCreatetime;
    }

    public Date getcModification() {
        return cModification;
    }

    public void setcModification(Date cModification) {
        this.cModification = cModification;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus == null ? null : cStatus.trim();
    }

    public String getcUpdate() {
        return cUpdate;
    }

    public void setcUpdate(String cUpdate) {
        this.cUpdate = cUpdate == null ? null : cUpdate.trim();
    }

    public String getcNotes() {
        return cNotes;
    }

    public void setcNotes(String cNotes) {
        this.cNotes = cNotes == null ? null : cNotes.trim();
    }

    public List<LegalPerson> getLegalPersons() { return legalPersons; }

    public void setLegalPersons(List<LegalPerson> legalPersons) { this.legalPersons = legalPersons;  }

    public List<Pic> getPics() { return pics; }

    public void setPics(List<Pic> pics) { this.pics = pics; }

    public List<Taxation> getTaxations() { return taxations; }

    public void setTaxations(List<Taxation> taxations) { this.taxations = taxations; }

    public String getcRegtimeStart() {  return cRegtimeStart; }

    public void setcRegtimeStart(String cRegtimeStart) { this.cRegtimeStart = cRegtimeStart; }

    public String getcRegtimeEnd() { return cRegtimeEnd; }

    public void setcRegtimeEnd(String cRegtimeEnd) { this.cRegtimeEnd = cRegtimeEnd; }

    public Double getTotalMoney() { return totalMoney; }

    public void setTotalMoney(Double totalMoney) { this.totalMoney = totalMoney; }

    public List<Earnings> getEarnings() { return earnings; }

    public void setEarnings(List<Earnings> earnings) { this.earnings = earnings; }
}