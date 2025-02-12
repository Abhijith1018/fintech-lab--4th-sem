package com.example.lab.entity;
import java.sql.Timestamp;
import java.sql.Date;

public interface AuditLoggable {
    void setEfctv_date(Date date);
    void setLocal_ts(Timestamp timestamp);
    void setHost_ts(Timestamp timestamp);
    void setAcpt_ts(Timestamp timestamp);
    void setAcpt_ts_utc_ofst(Timestamp timestamp);
    void setWs_id(String wsId);
    void setUser_id(String userId);
    void setPrgm_id(String prgmId);
    void setUUID(String UUID);
}


