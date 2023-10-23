package com.api.dtos;

import com.api.enums.ErrorEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {

	String message;
	Integer errorCode;
	
	public ErrorResponseDTO(ErrorEnum error) {
		this.message = error.getMessage();
		this.errorCode = error.getErrorCode();
	}
}
