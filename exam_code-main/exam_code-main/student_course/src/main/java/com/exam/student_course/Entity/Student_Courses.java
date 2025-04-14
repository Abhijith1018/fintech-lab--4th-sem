package com.exam.student_course.Entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_Courses")
public class Student_Courses implements AuditLoggable {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @Column(name = "student_course_crud_value")
    private Character crud_value;
    @Column(name = "student_course_efctv_date")
    private Date efctv_date;
    @Column(name = "student_course_local_ts")
    private Timestamp local_ts;
    @Column(name = "student_course_host_ts")
    private Timestamp host_ts;
    @Column(name = "student_course_acpt_ts")
    private Timestamp acpt_ts;
    @Column(name = "student_course_uuid")
    private String uuid;
    @Column(name = "student_course_user_id")
    private String user_id;
    @Column(name = "student_course_ws_id")
    private String ws_id;
    @Column(name = "student_course_prgm_id")
    private String prgm_id;


    public Student_Courses() {
        // Default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
