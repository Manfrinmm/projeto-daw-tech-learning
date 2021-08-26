package com.techlearning.backend.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techlearning.backend.exceptionHandler.AppError.Fields;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource msg;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<AppError.Fields> erro_campos = new ArrayList<Fields>();
				
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String mensagem = msg.getMessage(error, LocaleContextHolder.getLocale());
			
			erro_campos.add(new AppError.Fields(name, mensagem));
		}
		
		AppError appError = new AppError(status.value(),LocalDateTime.now(),"Verifique o preenchimento dos campos!",erro_campos);
		return handleExceptionInternal(ex, appError, headers, status, request);
	}
		
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<AppError> dataIntegrity (BusinessException ex) {
		AppError erro = new AppError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	
	/*
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidAuthenticationException.class)
	public ResponseEntity<AppError> invalidAuthentication (InvalidAuthenticationException ex) {
		AppError erro = new AppError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	*/
}
