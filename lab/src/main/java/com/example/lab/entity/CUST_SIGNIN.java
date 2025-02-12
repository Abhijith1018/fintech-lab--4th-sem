package com.example.lab.entity;


import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUST_SIGNIN")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CUST_SIGNIN implements AuditLoggable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTSIGN_IDFR")
    private Long id;
    @Column(name = "CSTSIGN_USRNAME")
    private String userName;
    @Column(name = "CSTSIGN_PASSWORD")
    private String password;
    @Column(name = "CSTSIGN_ROLE")
    private String role;
    @Column(name = "CSTSIGN_EFCTV_DATE")
    private Date efctv_date;
    @Column(name = "CSTSIGN_CRUD_VALUE")
    private char crud_value;
    @Column(name = "CSTSIGN_USER_ID")
    private String user_id;
    @Column(name = "CSTSIGN_WS_ID")
    private String ws_id;
    @Column(name = "CSTSIGN_PRGM_ID")
    private String prgm_id;
    @Column(name = "CSTSIGN_HOST_TS")
    private Timestamp host_ts;
    @Column(name = "CSTSIGN_LOCAL_TS")
    private Timestamp local_ts;
    @Column(name = "CSTSIGN_ACPT_TS")
    private Timestamp acpt_ts;
    @Column(name = "CSTSIGN_ACPT_TS_UTC_OFST")
    private Timestamp acpt_ts_utc_ofst;
    @Column(name = "CSTSIGN_UUID")
    private String UUID;

    @JoinColumn(name = "LDBID")
    @ManyToOne
    private FIN_INSTITUTIONS ldbid;

    @ManyToOne
    @JoinColumn(name = "CST_ID")
    private CUST_DETAILS cust_DETAILS;

    public void setCust_DETAILS(CUST_DETAILS cust_DETAILS)
    {
        this.cust_DETAILS = cust_DETAILS;
    }

    public CUST_DETAILS getCust_DETAILS()
    {
        return cust_DETAILS;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public FIN_INSTITUTIONS getLdbid() {
        return ldbid;
    }

    public void setLdbid(FIN_INSTITUTIONS ldbid) {
        this.ldbid = ldbid;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
