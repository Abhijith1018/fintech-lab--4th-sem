package com.exam.student_course.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.student_course.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
