package com.techlearning.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlearning.backend.models.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer>{

	
}
