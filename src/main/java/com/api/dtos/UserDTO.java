package com.api.dtos;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
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
	
	@NotNull(message = "Missing fields")
	@Length(min=2, message = "Invalid fields")
	private String firstName;
	
	@NotNull(message = "Missing fields")
	@Length(min=2, message = "Invalid fields")
	private String lastName;
	
	@NotNull(message = "Missing fields")
	@Email(message = "Invalid fields")
	private String email;
	
	@NotNull(message = "Missing fields")
	private Date birthday;
	
	@NotNull(message = "Missing fields")
	private String login;
	
	@NotNull(message = "Missing fields")
	private String password;
	
	@NotNull(message = "Missing fields")
	private String phone;
	
	private List<CarDTO> cars;
	
}
