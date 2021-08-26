package com.techlearning.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techlearning.backend.dtos.CourseDTO;
import com.techlearning.backend.dtos.InstructorDTO;
import com.techlearning.backend.dtos.StudentDTO;
import com.techlearning.backend.dtos.SubscriptionDTO;
import com.techlearning.backend.models.Course;
import com.techlearning.backend.models.Instructor;
import com.techlearning.backend.models.Student;
import com.techlearning.backend.models.StudentCourse;
import com.techlearning.backend.repositories.StudentCourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SubscriptionService {

	@Autowired
	private StudentCourseRepository subscriptionRepository;
	private StudentService studentService;
	private CourseService courseService;
	private InstructorService instructorService;

	public Page<SubscriptionDTO> findAll(Pageable pageable) {
		Page<StudentCourse> subscriptions = subscriptionRepository.findAll(pageable);
				
		return subscriptions.map(subscription -> new SubscriptionDTO(subscription));
	}
	
	@Transactional
	public SubscriptionDTO createSubscription(SubscriptionDTO subscription) {
		
		System.out.println(subscription.getStudent_id() + "subscription.getStudent_id()");
		StudentDTO studentDTO = studentService.findById(subscription.getStudent_id());
		Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getCpf(), studentDTO.getEmail(), studentDTO.getPassword());
		
		System.out.println(subscription.getCourse_id()+ "subscription.getCourse_id()");
		CourseDTO courseDTO = courseService.findById(subscription.getCourse_id());
		
		InstructorDTO instructorDto = instructorService.getInstructorById(courseDTO.getInstructor().getId());
		Instructor instructor = new Instructor(instructorDto.getId(), instructorDto.getName(), instructorDto.getCpf(), instructorDto.getEmail());
		
		Course course = new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getDescription(), courseDTO.getThumbnail(), instructor);
		
			
		return new SubscriptionDTO(subscriptionRepository.save(new StudentCourse(subscription.getId(), student, course)));			
	}
}
