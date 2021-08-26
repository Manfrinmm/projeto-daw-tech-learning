package com.techlearning.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlearning.backend.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByEmail(String email);
	Optional<Student> findByCpf(String cpf);
}
