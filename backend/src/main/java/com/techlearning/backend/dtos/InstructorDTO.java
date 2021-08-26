package com.techlearning.backend.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlearning.backend.models.Course;
import com.techlearning.backend.models.Instructor;

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
public class InstructorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Integer id ;

	@NotBlank
	private String name;
	
	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;
	
	@NotBlank
	@Email
	private String email;
	
	//private List<Course> courses;
	
	public InstructorDTO (Instructor instructor) {
		this.id = instructor.getId();
		this.name = instructor.getName();
		this.cpf = instructor.getCpf();
		this.email = instructor.getEmail();
		//this.courses = instructor.getCourses();
	}
}
