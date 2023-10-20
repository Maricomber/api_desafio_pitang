package com.api.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1013003210952856313L;
	private final String jwttoken;
}
