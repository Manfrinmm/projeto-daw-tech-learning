package com.techlearning.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techlearning.backend.dtos.CourseDTO;
import com.techlearning.backend.dtos.InstructorDTO;

import com.techlearning.backend.exceptionHandler.BusinessException;
import com.techlearning.backend.models.Course;
import com.techlearning.backend.models.Instructor;

import com.techlearning.backend.repositories.CourseRepository;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	private InstructorService instructorService;
	
	
	public Page<CourseDTO> findAll(Pageable pageable) {
		Page<Course> courses = courseRepository.findAll(pageable);
		
		return courses.map(course-> new CourseDTO(course));
	}
	
	public CourseDTO findById(Integer id) {
		 Course course = courseRepository.findById(id).orElseThrow(() -> new BusinessException("Curso não encontrado!"));
		 
		 return new CourseDTO(course);
	}
	
	@Transactional
	public CourseDTO createCourse(CourseDTO course) {
		InstructorDTO instructorDto = instructorService.getInstructorById(course.getInstructor_id());
		
		Instructor instructor = new Instructor(instructorDto.getId(), instructorDto.getName(), instructorDto.getCpf(), instructorDto.getEmail());
			
		return new CourseDTO(courseRepository.save(new Course(course.getId(), course.getName(), course.getDescription(), course.getThumbnail(), instructor)));			
	}
	
	@Transactional
	public CourseDTO updateCourse(CourseDTO course) {
		Course entity = courseRepository.findById(course.getId())
				.orElseThrow(() -> new BusinessException("Curso não encontrado!"));
		
			
		entity.setName(course.getName());
		entity.setDescription(course.getDescription());
		entity.setThumbnail(course.getThumbnail());
		
		InstructorDTO instructorDto = instructorService.getInstructorById(course.getInstructor_id());
		Instructor instructor = new Instructor(instructorDto.getId(), instructorDto.getName(), instructorDto.getCpf(), instructorDto.getEmail());
		
		entity.setInstructor(instructor);
		

		return new CourseDTO(courseRepository.save(entity));
	}	
	
	@Transactional
	public void deleteCourse(Integer id) {
		Course Course = courseRepository.findById(id).orElseThrow(() -> new BusinessException("Curso não encontrado!"));
		
		courseRepository.delete(Course);
	}
}
