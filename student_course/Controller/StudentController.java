package com.exam.student_course.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.exam.student_course.DTO.studentDTO;
import com.exam.student_course.DTO.courseDTO;
import com.exam.student_course.DTO.enrollDTO;
import com.exam.student_course.Entity.Course;
import com.exam.student_course.Entity.Student;
import com.exam.student_course.Service.studentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class StudentController {

    @Autowired
    private studentService service;


    @PostMapping("/students")
    public Student createStudent(@RequestBody studentDTO student) {
        
        return service.saveStudent(student);
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody courseDTO course) {
        
        return service.saveCourse(course);
    }
    
    @PostMapping("/enroll")
    public String enroll(@RequestBody enrollDTO enroll) {
        
        return service.enrollStudent(enroll);
    }
    
    @GetMapping("students/{id}/courses")
    public List<Course> getMethodName(@PathVariable Integer id) {
        return service.getCourses(id);
    }
    
}
