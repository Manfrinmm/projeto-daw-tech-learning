package com.techlearning.backend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlearning.backend.models.Course;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CourseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String thumbnail;
	
	@NotNull
	@Range(min = 1)
	private Integer instructor_id;

	
	private InstructorDTO instructor;
	
	public CourseDTO (Course course) {
		this.id = course.getId();
		this.name = course.getName();
		this.description = course.getDescription();
		this.thumbnail = course.getThumbnail();
		
		if(course.getInstructor() != null) {
			this.instructor = new InstructorDTO(course.getInstructor());	
		}
	}
}
