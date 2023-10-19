package com.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrosEnum {
	
	UNAUTHORIZED(1, "Unauthorized"),
	TOKEN_EXPIRED(2, "Unauthorized - invalid session"),
	PLATE_EXISTS(3, "Lincense plate already exists"),
	INVALID_FIELDS(4, "Invalid fields"),
	MISSING_FIELDS(5, "Missing fields");
	
	private int errorCode;
	private String message;

}
