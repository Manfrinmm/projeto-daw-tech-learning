package com.techlearning.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlearning.backend.models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
	List<Lesson> findAllByCourse(Integer course_id);
	
}
