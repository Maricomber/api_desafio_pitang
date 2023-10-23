package com.api.exception;

import com.api.enums.ErrorEnum;

import lombok.Getter;

@Getter
public class ApiPitangException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7962493637806140249L;

	ErrorEnum error;
	
	public ApiPitangException(ErrorEnum error) {
		super(error.toString());
		this.error = error;
	}
}
