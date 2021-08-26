package com.techlearning.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techlearning.backend.dtos.SubscriptionDTO;
import com.techlearning.backend.services.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Student and Course Registration")
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Operation(summary = "List all subscriptions")
	@GetMapping
	public ResponseEntity<Page<SubscriptionDTO>> index(Pageable pageable) {
		Page<SubscriptionDTO> courses = subscriptionService.findAll(pageable);
		
		return ResponseEntity.ok(courses);
	}
		
	
	@Operation(summary = "Create subscription")
	@PostMapping
	public ResponseEntity<SubscriptionDTO> create(@Valid @RequestBody SubscriptionDTO subscriptionBody) {
		SubscriptionDTO subscription = subscriptionService.createSubscription(subscriptionBody);
	
		return ResponseEntity.created(null).body(subscription);
	}
	
	
	/*
	 * 
	 *
	
	

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
