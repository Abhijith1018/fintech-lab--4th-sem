package com.example.lab.entity;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUST_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CUST_DETAILS implements AuditLoggable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTDET_IDFR")
    private Long idfr;
    @Column(name = "CST_ID")
    private Long id;
    @Column(name = "CSTDET_FULL_NAME")
    private String fullName;
    @Column(name = "CSTDET_DOB")
    private Date dob;
    @Column(name = "CSTDET_STATUS")
    private String status;
    /*@Column(name = "CSTDET_CONTACT")
    private String contact;*/
    @Column(name = "CSTDET_MOBILE")
    private String mobile;
    @Column(name = "CSTDET_EMAIL")
    private String email;
    @Column(name = "CSTDET_COUNTRY")
    private String country;
    @Column(name = "CSTDET_EFCTV_DATE")
    private Date efctv_date;
    @Column(name = "CSTDET_CRUD_VALUE")
    private char crud_value;
    @Column(name = "CSTDET_USER_ID")
    private String user_id;
    @Column(name = "CSTDET_WS_ID")
    private String ws_id;
    @Column(name = "CSTDET_PRGM_ID")
    private String prgm_id;
    @Column(name = "CSTDET_HOST_TS")
    private Timestamp host_ts;
    @Column(name = "CSTDET_LOCAL_TS")
    private Timestamp local_ts;
    @Column(name = "CSTDET_ACPT_TS")
    private Timestamp acpt_ts;
    @Column(name = "CSTDET_ACPT_TS_UTC_OFST")
    private Timestamp acpt_ts_utc_ofst;
    @Column(name = "CSTDET_UUID")
    private String UUID;

    @JoinColumn(name = "LDBID")
    @ManyToOne
    private FIN_INSTITUTIONS ldbid;

    @ManyToOne
    @JoinColumn(name = "CSTDET_TYPE")
    private CUST_CL type;

    /*@OneToMany
    @JoinColumn(name = "fk_CST_ID", referencedColumnName = "CST_ID")
    private List<CUST_NAME> cust_NAME;

    // Getters and Setters

    public void setCust_NAME(List<CUST_NAME> cust_NAME)
    {
        this.cust_NAME = cust_NAME;
    }

    public List<CUST_NAME> getCust_NAME()
    {
        return cust_NAME;
    }*/

    public Long getIdfr() {
        return idfr;
    }

    public void setIdfr(Long idfr) {
        this.idfr = idfr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CUST_CL getType() {
        return type;
    }

    public void setType(CUST_CL type) {
        this.type = type;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }*/

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getEfctv_date() {
        return efctv_date;
    }

    public void setEfctv_date(Date efctv_date) {
        this.efctv_date = efctv_date;
    }

    public char getCrud_value() {
        return crud_value;
    }

    public void setCrud_value(char crud_value) {
        this.crud_value = crud_value;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWs_id() {
        return ws_id;
    }

    public void setWs_id(String ws_id) {
        this.ws_id = ws_id;
    }

    public String getPrgm_id() {
        return prgm_id;
    }

    public void setPrgm_id(String prgm_id) {
        this.prgm_id = prgm_id;
    }

    public Timestamp getHost_ts() {
        return host_ts;
    }

    public void setHost_ts(Timestamp host_ts) {
        this.host_ts = host_ts;
    }

    public Timestamp getLocal_ts() {
        return local_ts;
    }

    public void setLocal_ts(Timestamp local_ts) {
        this.local_ts = local_ts;
    }

    public Timestamp getAcpt_ts() {
        return acpt_ts;
    }

    public void setAcpt_ts(Timestamp acpt_ts) {
        this.acpt_ts = acpt_ts;
    }

    public Timestamp getAcpt_ts_utc_ofst() {
        return acpt_ts_utc_ofst;
    }

    public void setAcpt_ts_utc_ofst(Timestamp acpt_ts_utc_ofst) {
        this.acpt_ts_utc_ofst = acpt_ts_utc_ofst;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public FIN_INSTITUTIONS getLdbid() {
        return ldbid;
    }

    public void setLdbid(FIN_INSTITUTIONS ldbid) {
        this.ldbid = ldbid;
    }
}


