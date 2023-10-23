package com.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorEnum {
	UNAUTHORIZED(1, "Unauthorized"),
	TOKEN_EXPIRED(2, "Unauthorized - invalid session"),
	PLATE_EXISTS(3, "Lincense plate already exists"),
	INVALID_FIELDS(4, "Invalid fields"),
	MISSING_FIELDS(5, "Missing fields"),
	LOGIN_INEXISTENTE(6, "Invalid login or password"),
	EMAIL_ALREADY_EXISTS(7, "Email already exists"),
	LOGIN_ALREADY_EXISTS(8, "Login already exists"),
	GENERAL(9999,"Other errors");
	
	private int errorCode;
	private String message;
	
	public static ErrorEnum getByMessage(String message) {
		for (ErrorEnum s : ErrorEnum.values()) {
		    if (s.getMessage().equals(message)) {
		        return s;
		    }
		}
		return GENERAL;
	}
}
