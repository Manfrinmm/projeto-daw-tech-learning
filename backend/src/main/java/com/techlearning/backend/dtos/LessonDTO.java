package com.techlearning.backend.dtos;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlearning.backend.models.Lesson;

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
public class LessonDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank
	private String name;
	
	@NotBlank
	private String video;
	
	private Integer course_id;
	
	private CourseDTO course;
	
	public LessonDTO (Lesson lesson) {
		this.id = lesson.getId();
		this.name = lesson.getName();
		this.video = lesson.getVideo();
		this.course = new CourseDTO(lesson.getCourse());
	}
}
