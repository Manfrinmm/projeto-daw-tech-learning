package com.techlearning.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlearning.backend.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
}
