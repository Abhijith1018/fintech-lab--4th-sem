package com.exam.student_course.Service.helper;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.exam.student_course.Entity.AuditLoggable;

@Component
public class studentServiceHelper {
    
    // sets the audit logs(for all since they implement the interface)
    public <T extends AuditLoggable>void setAuditLog(T cust_DETAILS)
    {
        cust_DETAILS.setEfctv_date(new Date(System.currentTimeMillis()));
        cust_DETAILS.setLocal_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setHost_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setAcpt_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setWs_id("Jaiwant_WS");
        cust_DETAILS.setUser_id("Jaiwant");
        cust_DETAILS.setPrgm_id("Java prgm 1");
        cust_DETAILS.setUUID(UUID.randomUUID().toString());
    }
}