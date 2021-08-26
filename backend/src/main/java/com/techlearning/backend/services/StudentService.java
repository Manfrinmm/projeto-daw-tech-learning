package com.techlearning.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techlearning.backend.dtos.StudentDTO;
import com.techlearning.backend.exceptionHandler.BusinessException;
import com.techlearning.backend.models.Student;
import com.techlearning.backend.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Page<StudentDTO> findAll(Pageable pageable) {
		Page<Student> users = studentRepository.findAll(pageable);
		
		//return users.stream().map(user-> new UserDTO(user)).collect(Collectors.toList());
		
		return users.map(user-> new StudentDTO(user));
	}
	
	@Transactional
	public StudentDTO createStudent(StudentDTO student) {		
		Boolean emailAlreadyUsed = studentRepository.findByEmail(student.getEmail()).stream().anyMatch(prop->!prop.equals(student));
	
		if(emailAlreadyUsed) {
			throw new BusinessException("E-mail já utilizado");
		}
		
		Boolean cpfAlreadyUsed = studentRepository.findByCpf(student.getCpf()).stream().anyMatch(prop->!prop.equals(student));
		
		if(cpfAlreadyUsed) {
			throw new BusinessException("CPF já utilizado");
		}
		
		return new StudentDTO(studentRepository.save(new Student(student.getId(), student.getName(), student.getCpf(), student.getEmail(), student.getPassword())));			
	}
		
	public StudentDTO findByEmail(String email) {
		Student student = studentRepository.findByEmail(email).orElseThrow(() -> new BusinessException("Aluno não encontrado!"));
		 
		return new StudentDTO(student);
	}
		
	public StudentDTO findByCpf(String cpf) {
		 Student student = studentRepository.findByCpf(cpf).orElseThrow(() -> new BusinessException("Aluno não encontrado!"));
		 
		 return new StudentDTO(student);
	}
	
	public StudentDTO findById(Integer id) {
		 Student student = studentRepository.findById(id).orElseThrow(() -> new BusinessException("Aluno não encontrado!"));
		 
		 return new StudentDTO(student);
	}
	
	@Transactional
	public StudentDTO update(StudentDTO student) {
		Student entity = studentRepository.findById(student.getId())
				.orElseThrow(() -> new BusinessException("Aluno não encontrado!"));
		
		Boolean emailAlreadyUsed = studentRepository.findByEmail(student.getEmail()).stream().anyMatch(prop->!prop.equals(student));
		
		if(emailAlreadyUsed && !entity.getEmail().equals(student.getEmail())) {
			throw new BusinessException("E-mail já utilizado");
		}
		
		Boolean cpfAlreadyUsed = studentRepository.findByCpf(student.getCpf()).stream().anyMatch(prop->!prop.equals(student));
		
		if(cpfAlreadyUsed && !entity.getCpf().equals(student.getCpf())) {
			throw new BusinessException("CPF já utilizado");
		}
		
		entity.setName(student.getName());
		entity.setCpf(student.getCpf());
		entity.setEmail(student.getEmail());
		//entity.setPassword(student.getPassword());

		return new StudentDTO(studentRepository.save(entity));
	}	
	
	@Transactional
	public void deleteStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new BusinessException("Aluno não encontrado!"));
		
		studentRepository.delete(student);
	}
}
