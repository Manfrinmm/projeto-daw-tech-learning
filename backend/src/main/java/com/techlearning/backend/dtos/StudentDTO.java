package com.techlearning.backend.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlearning.backend.models.Student;
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
public class StudentDTO implements Serializable {
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
	
	@NotBlank
	private String password;
	
	private List<StudentCourse> courses;
	
	public StudentDTO (Student student) {
		this.id = student.getId();
		this.name = student.getName();
		this.email = student.getEmail();
		//this.courses = student.getCourses();
	}
}
