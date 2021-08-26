package com.techlearning.backend.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techlearning.backend.dtos.InstructorDTO;
import com.techlearning.backend.exceptionHandler.BusinessException;
import com.techlearning.backend.models.Instructor;
import com.techlearning.backend.repositories.InstructorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Transactional
	public InstructorDTO createInstructor(InstructorDTO instructor) {		
		Boolean emailAlreadyUsed = instructorRepository.findByEmail(instructor.getEmail()).stream().anyMatch(prop->!prop.equals(instructor));
	
		if(emailAlreadyUsed) {
			throw new BusinessException("E-mail já utilizado");
		}
		
		Boolean cpfAlreadyUsed = instructorRepository.findByCpf(instructor.getCpf()).stream().anyMatch(prop->!prop.equals(instructor));
		
		if(cpfAlreadyUsed) {
			throw new BusinessException("CPF já utilizado");
		}
		
		return new InstructorDTO(instructorRepository.save(new Instructor(instructor.getId(), instructor.getName(), instructor.getCpf(), instructor.getEmail())));			
	}
	
	
	public InstructorDTO getInstructorById(Integer id) {		
		Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new BusinessException("Instrutor não encontrado!"));
	
		return new InstructorDTO(instructor);			
	}
}
