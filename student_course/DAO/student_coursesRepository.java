package com.exam.student_course.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.student_course.Entity.Student_Courses;

public interface student_coursesRepository extends JpaRepository<Student_Courses, Integer> {
    
    @Query("SELECT c from Student_Courses c WHERE c.student.id = :id") // Custom query to fetch courses for a given student ID
    List<Student_Courses> findCoursesByStudentId(@Param("id") int studentId); // Fetch courses for a given student ID
}
