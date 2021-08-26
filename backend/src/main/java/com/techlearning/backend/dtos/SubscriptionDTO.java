package com.techlearning.backend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlearning.backend.models.StudentCourse;

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
public class SubscriptionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotNull
	@Range(min = 1)
	private Integer course_id;
	
	private CourseDTO course;
	
	@NotNull
	@Range(min = 1)
	private Integer student_id;
	
	private StudentDTO student;

	
	public SubscriptionDTO (StudentCourse subscription) {
		this.id = subscription.getId();
		this.course = new CourseDTO(subscription.getCourse());
		this.student = new StudentDTO(subscription.getStudent());
	}
}
