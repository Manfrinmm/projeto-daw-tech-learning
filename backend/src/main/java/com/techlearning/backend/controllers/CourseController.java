package com.techlearning.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.techlearning.backend.dtos.CourseDTO;
import com.techlearning.backend.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Courses")
@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@Operation(summary = "List all courses")
	@GetMapping
	public ResponseEntity<Page<CourseDTO>> index(Pageable pageable) {
		Page<CourseDTO> courses = courseService.findAll(pageable);
		
		return ResponseEntity.ok(courses);
	}
	
	@Operation(summary = "Create course")
	@PostMapping
	public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO courseDTO) {		
		CourseDTO course = courseService.createCourse(courseDTO);
		
		return ResponseEntity.created(null).body(course);
	}
	
	@Operation(summary = "Show course")
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> show(@PathVariable Integer id) {
		CourseDTO courseDTO = courseService.findById(id);
		
		return ResponseEntity.ok(courseDTO); 
	}

	@Operation(summary = "Update course")
	@PutMapping("/{id}")
	public ResponseEntity<CourseDTO> update(@PathVariable Integer id, @RequestBody CourseDTO objBody) {
		
		objBody.setId(id);
		
		CourseDTO objDTO = courseService.updateCourse(objBody);
		
		return ResponseEntity.ok(objDTO);
	}
	
	@Operation(summary = "Delete course")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		courseService.deleteCourse(id);
		
		return ResponseEntity.noContent().build(); 
	}
}
