package com.techlearning.backend.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class AppError {
	private Integer codigo;
	private LocalDateTime momento;
	private String decricao;
	private List<Fields> campos;


	@AllArgsConstructor
	@Setter
	@Getter
	public static class Fields {
		private String nome;
		private String mensagem;
	}
}
