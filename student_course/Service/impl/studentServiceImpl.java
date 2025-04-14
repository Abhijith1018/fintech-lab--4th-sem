package com.exam.student_course.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.student_course.DAO.CourseRepository;
import com.exam.student_course.DAO.StudentRepository;
import com.exam.student_course.DAO.student_coursesRepository;
import com.exam.student_course.DTO.courseDTO;
import com.exam.student_course.DTO.enrollDTO;
import com.exam.student_course.DTO.studentDTO;
import com.exam.student_course.Entity.Course;
import com.exam.student_course.Entity.Student;
import com.exam.student_course.Entity.Student_Courses;
import com.exam.student_course.Service.studentService;
import com.exam.student_course.Service.helper.studentServiceHelper;

@Service
public class studentServiceImpl implements studentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    student_coursesRepository stu_course_repo;
    @Autowired
    studentServiceHelper helper;
    
    @Override
    public Student saveStudent(studentDTO student) {
        Student studentEntity = new Student();
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Student email cannot be null or empty");
        }
        if (student.getEmail().contains("@") == false) {
            throw new IllegalArgumentException("Student email must contain '@' symbol");
        }
        if (student.getEmail().contains(".") == false) {
            throw new IllegalArgumentException("Student email must contain '.' symbol");
        }
        studentEntity.setName(student.getName());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setCrud_value('C'); // Set the CRUD value to 'C' for create operation
        helper.setAuditLog(studentEntity); // Set audit logs using the helper method
        return studentRepository.save(studentEntity); // Save the student entity to the database
    }

    @Override
    public Course saveCourse(courseDTO course) {
        Course courseEntity = new Course();
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        if (course.getDescription() == null || course.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Course description cannot be null or empty");
        }
        courseEntity.setName(course.getName());
        courseEntity.setDescription(course.getDescription());
        courseEntity.setCrud_value('C');// Set the CRUD value to 'C' for create operation
        helper.setAuditLog(courseEntity); // Set audit logs using the helper method
        return courseRepository.save(courseEntity); // Save the course entity to the database
    }

    @Override
    public List<Course> getCourses(int id) {
        List<Student_Courses> all_courses =  stu_course_repo.findCoursesByStudentId(id); 
        List<Course> courses = new ArrayList<>(); 
        if (all_courses.isEmpty()) {
            return courses; // Return an empty list if no courses are found for the student
        }

        for (Student_Courses studentCourse : all_courses) {
            Course course = studentCourse.getCourse(); 
            courses.add(course); 
        }
        return courses; 
    }

    @Override
    public String enrollStudent(enrollDTO enroll) {
        Student student = studentRepository.findById(enroll.getStudent_id()).orElse(null);
        Course course = courseRepository.findById(enroll.getCourse_id()).orElse(null);

        if (student == null || course == null) {
            throw new IllegalArgumentException("Invalid student or course ID"); 
        }

        Student_Courses studentCourse = new Student_Courses();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setCrud_value('C'); // Set the CRUD value to 'C' for create operation
        helper.setAuditLog(studentCourse); // Set audit logs using the helper method

        stu_course_repo.save(studentCourse); // Save the student-course entity to the database
        return "Enrolled successfully";
    }
    
}
