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
import jakarta.persistence.Table;

@Entity
@Table(name = "CUST_CL")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CUST_CL implements AuditLoggable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTCL_ID")
    private Long id;
    @Column(name = "CSTCL_TYP")
    private String type;
    @Column(name = "CSTCL_TYP_VALUE")
    private String typeValue;
    @Column(name = "CSTCL_EFCTV_DATE")
    private Date efctv_date;
    @Column(name = "CSTCL_CRUD_VALUE")
    private char crud_value;
    @Column(name = "CSTCL_USER_ID")
    private String user_id;
    @Column(name = "CSTCL_WS_ID")
    private String ws_id;
    @Column(name = "CSTCL_PRGM_ID")
    private String prgm_id;
    @Column(name = "CSTCL_HOST_TS")
    private Timestamp host_ts;
    @Column(name = "CSTCL_LOCAL_TS")
    private Timestamp local_ts;
    @Column(name = "CSTCL_ACPT_TS")
    private Timestamp acpt_ts;
    @Column(name = "CSTCL_ACPT_TS_UTC_OFST")
    private Timestamp acpt_ts_utc_ofst;
    @Column(name = "CSTCL_UUID")
    private String UUID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypeValue(String typeValue)
    {
        this.typeValue = typeValue;
    }

    public String getTypeValue()
    {
        return typeValue;
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
}






