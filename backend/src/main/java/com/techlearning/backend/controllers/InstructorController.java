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
import com.techlearning.backend.services.InstructorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
		
	@GetMapping
	public ResponseEntity<Page<InstructorDTO>> index(Pageable pageable) {
		Page<InstructorDTO> instructors = instructorService.findAll(pageable);
		
		return ResponseEntity.ok(instructors);
	}
	
	@Operation(summary = "Create instructor")
	@PostMapping
	public ResponseEntity<InstructorDTO> create(@Valid @RequestBody InstructorDTO instructorDTO) {
		InstructorDTO instructor = instructorService.createInstructor(instructorDTO);
		
		return ResponseEntity.created(null).body(instructor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InstructorDTO> show(@PathVariable Integer id) {
		InstructorDTO studentDTO = instructorService.getInstructorById(id);
		
		return ResponseEntity.ok(studentDTO); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<InstructorDTO> update(@PathVariable Integer id, @Valid @RequestBody InstructorDTO instructorBody) {
		instructorBody.setId(id);
		
		InstructorDTO instructorDTO = instructorService.update(instructorBody);
		
		return ResponseEntity.ok(instructorDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		instructorService.delete(id);
		
		return ResponseEntity.noContent().build(); 
	}
}
