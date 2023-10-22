package com.api.configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.dtos.ErrorResponseDTO;
import com.api.enums.ErrorEnum;
import com.api.exception.ApiPitangException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
	                                                              HttpStatus status, WebRequest request) {
		String messageError = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		ErrorResponseDTO errorDto = new ErrorResponseDTO(ErrorEnum.getByMessage(messageError));
		return ResponseEntity.badRequest().body(errorDto);
	}
	
	 @ExceptionHandler(ApiPitangException.class)
	    public @ResponseBody ResponseEntity<ErrorResponseDTO> handleNotAuthenticatedException(
	    		ApiPitangException ex, 
	            HttpServletRequest request) {
	        return new ResponseEntity<ErrorResponseDTO>(
	            new ErrorResponseDTO(ex.getError()), 
	            HttpStatus.UNAUTHORIZED 
	        );
	    }
}
