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

import com.techlearning.backend.dtos.StudentDTO;
import com.techlearning.backend.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Students")
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
			
	@Operation(summary = "List all students")
	@GetMapping
	public ResponseEntity<Page<StudentDTO>> index(Pageable pageable) {
		Page<StudentDTO> users = studentService.findAll(pageable);
		
		return ResponseEntity.ok(users);
	}
	
	@Operation(summary = "Create student")
	@PostMapping
	public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) {
		StudentDTO student = studentService.createStudent(studentDTO);
		
		return ResponseEntity.created(null).body(student);
	}
	
	@Operation(summary = "Show student")
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> show(@PathVariable Integer id) {
		StudentDTO studentDTO = studentService.findById(id);
		
		return ResponseEntity.ok(studentDTO); 
	}
	
	//Aula07_Mod_4
	@Operation(summary = "Update student")
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> update(@PathVariable  Integer id, @RequestBody StudentDTO objBody) {
		objBody.setId(id);
		StudentDTO objDTO = studentService.update(objBody);
		
		return ResponseEntity.ok(objDTO);
	}
	
	@Operation(summary = "Remove student")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		studentService.deleteStudent(id);
		
		return ResponseEntity.noContent().build(); 
	}
}
