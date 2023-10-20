package com.api.dtos;

import java.sql.Date;
import java.util.List;

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
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date birthday;
	
	private String login;
	
	private String password;
	
	private String phone;
	
	private List<CarDTO> cars;
	
}
