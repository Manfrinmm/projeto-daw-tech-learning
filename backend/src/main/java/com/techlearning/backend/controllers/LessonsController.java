package com.techlearning.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlearning.backend.dtos.LessonDTO;
import com.techlearning.backend.services.LessonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Lessons")
@RestController
@RequestMapping("/courses/{course_id}/lessons")
public class LessonsController {
	@Autowired
	private LessonService lessonService;
	
	@Operation(summary = "List all lessons")
	@GetMapping
	public ResponseEntity<List<LessonDTO>> index(@PathVariable Integer course_id, Pageable pageable) {
		//Stream<Object> lessons = lessonService.findAll(course_id);
		List<LessonDTO> lessons = lessonService.findAll(course_id, pageable);
		
		return ResponseEntity.ok(lessons);
	}
	
	@Operation(summary = "Create lesson")
	@PostMapping
	public ResponseEntity<LessonDTO> create(@PathVariable Integer course_id, @Valid @RequestBody LessonDTO lessonDTO) {		
		System.out.println("----------------- " + course_id + " ----------------------");
		
		
		lessonDTO.setCourse_id(course_id);
		
		LessonDTO lesson = lessonService.createLesson(lessonDTO);
		
		
		return ResponseEntity.created(null).body(lesson);
	}
	
	@Operation(summary = "Show lesson")
	@GetMapping("/{id}")
	public ResponseEntity<LessonDTO> show(@PathVariable Integer course_id, @PathVariable Integer id) {
		LessonDTO lessonDTO = lessonService.findById(course_id, id);
		
		return ResponseEntity.ok(lessonDTO); 
	}

	@Operation(summary = "Update lesson")
	@PutMapping("/{id}")
	public ResponseEntity<LessonDTO> update(@PathVariable Integer course_id, @PathVariable Integer id, @RequestBody LessonDTO lessonDTO) {
		
		lessonDTO.setId(id);
		lessonDTO.setCourse_id(course_id);
		
		LessonDTO objDTO = lessonService.updateLesson(lessonDTO);
		
		return ResponseEntity.ok(objDTO);
	}
	
	@Operation(summary = "Delete lesson")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer course_id, @PathVariable Integer id) {
		
		
		lessonService.deleteLesson(course_id,id);
		
		return ResponseEntity.noContent().build(); 
	}
}
