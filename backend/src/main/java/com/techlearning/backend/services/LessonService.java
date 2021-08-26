package com.techlearning.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techlearning.backend.dtos.LessonDTO;
import com.techlearning.backend.dtos.CourseDTO;
import com.techlearning.backend.dtos.InstructorDTO;
import com.techlearning.backend.exceptionHandler.BusinessException;
import com.techlearning.backend.models.Lesson;
import com.techlearning.backend.models.Course;
import com.techlearning.backend.models.Instructor;

import com.techlearning.backend.repositories.LessonRepository;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LessonService {

	@Autowired
	private LessonRepository lessonRepository;
	private InstructorService instructorService;
	private CourseService courseService;
	
	
	public List<LessonDTO> findAll(Integer course_id, Pageable pageable) {
		Streamable<Lesson> lessons = lessonRepository.findAll(pageable).filter(lesson -> lesson.getCourse().getId() == course_id);
		
		return lessons.map(lesson -> new LessonDTO(lesson)).toList();
	}
	
	public LessonDTO findById(Integer course_id, Integer id) {
		 Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new BusinessException("Aula não encontrada!"));
		 
		 if(lesson.getCourse().getId() != course_id) {	
				throw new BusinessException("Esta aula não pertence a este cruso!");
			}
		 
		 return new LessonDTO(lesson);
	}
	
	@Transactional
	public LessonDTO createLesson(LessonDTO lesson) {
		CourseDTO courseDTO = courseService.findById(lesson.getCourse_id());
		
		InstructorDTO instructorDTO = instructorService.getInstructorById(courseDTO.getInstructor().getId());
		Instructor instructor = new Instructor(instructorDTO.getId(), instructorDTO.getName(), instructorDTO.getCpf(), instructorDTO.getEmail());
		
		Course course = new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getDescription(), courseDTO.getThumbnail(), instructor);
			
		return new LessonDTO(lessonRepository.save(new Lesson(lesson.getId(), lesson.getName(), lesson.getVideo(), course)));			
	}
	
	@Transactional
	public LessonDTO updateLesson(LessonDTO lesson) {
		Lesson entity = lessonRepository.findById(lesson.getId())
				.orElseThrow(() -> new BusinessException("Aula não encontrada!"));
		
		if(entity.getCourse().getId() != lesson.getCourse_id()) {	
			throw new BusinessException("Esta aula não pertence a este cruso!");
		}
			
		entity.setName(lesson.getName());
		entity.setVideo(lesson.getVideo());
		
		return new LessonDTO(lessonRepository.save(entity));
	}	
	
	@Transactional
	public void deleteLesson(Integer course_id, Integer id) {
		Lesson entity = lessonRepository.findById(id).orElseThrow(() -> new BusinessException("Aula não encontrada!"));
		
		if(entity.getCourse().getId() != course_id) {	
			throw new BusinessException("Esta aula não pertence a este cruso!");
		}
		
		
		lessonRepository.delete(entity);
	}
}
