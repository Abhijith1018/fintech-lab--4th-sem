package com.exam.student_course.Entity;



import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
public class Course implements AuditLoggable {
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "course_name")
    private String name;
    @Column(name = "course_description")
    private String description;
    @Column(name = "course_crud_value")
    private Character crud_value;
    @Column(name = "course_efctv_date")
    private Date efctv_date;
    @Column(name = "course_local_ts")
    private Timestamp local_ts;
    @Column(name = "course_host_ts")
    private Timestamp host_ts;
    @Column(name = "course_acpt_ts")
    private Timestamp acpt_ts;
    @Column(name = "course_uuid")
    private String uuid;
    @Column(name = "course_user_id")
    private String user_id;
    @Column(name = "course_ws_id")
    private String ws_id;
    @Column(name = "course_prgm_id")
    private String prgm_id;

    public Course() {
        // Default constructor
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getCrud_value() {
        return crud_value;
    }

    public void setCrud_value(Character crud_value) {
        this.crud_value = crud_value;
    }

    public Date getEfctv_date() {
        return efctv_date;
    }

    public void setEfctv_date(Date efctv_date) {
        this.efctv_date = efctv_date;
    }

    public Timestamp getLocal_ts() {
        return local_ts;
    }

    public void setLocal_ts(Timestamp local_ts) {
        this.local_ts = local_ts;
    }

    public Timestamp getHost_ts() {
        return host_ts;
    }

    public void setHost_ts(Timestamp host_ts) {
        this.host_ts = host_ts;
    }

    public Timestamp getAcpt_ts() {
        return acpt_ts;
    }

    public void setAcpt_ts(Timestamp acpt_ts) {
        this.acpt_ts = acpt_ts;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
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
    
}
