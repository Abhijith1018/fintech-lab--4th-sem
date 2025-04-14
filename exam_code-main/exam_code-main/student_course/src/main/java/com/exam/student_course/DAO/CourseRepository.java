package com.exam.student_course.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.student_course.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    
}
