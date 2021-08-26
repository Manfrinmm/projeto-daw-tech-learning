package com.techlearning.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name="instructors")
public class Instructor implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="cpf",nullable = false)
	private String cpf;
	
	@Column(name="email",nullable = false)
	private String email;
	
	@OneToMany
	@JoinColumn(name = "instructor_id")
	private List<Course> courses = new ArrayList<Course>();

	public Instructor(Integer id, String name, String cpf, String email) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}	
}
