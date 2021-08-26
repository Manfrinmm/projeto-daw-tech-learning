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

import com.techlearning.backend.dtos.InstructorDTO;
import com.techlearning.backend.dtos.StudentDTO;
import com.techlearning.backend.models.Instructor;
import com.techlearning.backend.services.InstructorService;
import com.techlearning.backend.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
		
	
	@Operation(summary = "Create instructor")
	@PostMapping
	public ResponseEntity<InstructorDTO> create(@Valid @RequestBody InstructorDTO instructorDTO) {
		InstructorDTO instructor = instructorService.createInstructor(instructorDTO);
		
		return ResponseEntity.created(null).body(instructor);
	}
	/*
	@GetMapping
	public ResponseEntity<Page<StudentDTO>> index(Pageable pageable) {
		Page<StudentDTO> users = instructorService.findAll(pageable);
		
		return ResponseEntity.ok(users);
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> show(@PathVariable Integer id) {
		StudentDTO studentDTO = instructorService.findById(id);
		
		return ResponseEntity.ok(studentDTO); 
	}
	
	@PutMapping
	public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO objBody) {
		StudentDTO objDTO = studentService.update(objBody);
		
		return ResponseEntity.ok(objDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		studentService.deleteStudent(id);
		
		return ResponseEntity.noContent().build(); 
	}*/
}
