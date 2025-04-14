package com.exam.student_course.Service;

import java.util.List;

import com.exam.student_course.DTO.courseDTO;
import com.exam.student_course.DTO.enrollDTO;
import com.exam.student_course.DTO.studentDTO;
import com.exam.student_course.DTO.enrollDTO;
import com.exam.student_course.Entity.Course;
import com.exam.student_course.Entity.Student;

public interface studentService {
    public Student saveStudent(studentDTO student); // Method to save student details
    public Course saveCourse(courseDTO course); // Method to save course details
    public String enrollStudent(enrollDTO enroll); // Method to enroll a student in a course
    public List<Course> getCourses(int id); // Method to get student details by ID
    
}
