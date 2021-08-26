package com.techlearning.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlearning.backend.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
	Optional<Instructor> findByEmail(String email);
	Optional<Instructor> findByCpf(String cpf);
}
