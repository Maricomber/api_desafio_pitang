package com.api.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtRequestDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 782212199643761603L;
	
	@NotBlank(message = "Missing fields")
	private String username;
	
	@NotBlank(message = "Missing fields")
	private String password;

}
