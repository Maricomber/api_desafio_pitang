package com.api.dtos;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer idUser;
	
	@NotBlank(message = "Missing fields")
	@Length(min=2, message = "Invalid fields")
	private String firstName;
	
	@NotBlank(message = "Missing fields")
	@Length(min=2, message = "Invalid fields")
	private String lastName;
	
	@NotBlank(message = "Missing fields")
	@Email(message = "Invalid fields")
	private String email;
	
	@NotNull(message = "Missing fields")
	private Date birthday;
	
	@NotBlank(message = "Missing fields")
	private String login;
	
	@NotBlank(message = "Missing fields")
	private String password;
	
	@NotBlank(message = "Missing fields")
	private String phone;
	
	private List<CarDTO> cars;
	
}
