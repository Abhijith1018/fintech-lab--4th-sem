package com.exam.student_course.DTO;

public class enrollDTO {
    private Integer student_id;
    private Integer course_id;
    public enrollDTO(Integer student_id, Integer course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }
    public enrollDTO() {
        // Default constructor
    }
    public Integer getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
    public Integer getCourse_id() {
        return course_id;
    }
    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }
}
