package com.api.dtos;

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
public class CarDTO {

	private Integer idCar;
	
	@Length(min = 4, max = 4, message = "Invalid fields")
	@NotNull(message = "Missing fields")
	private int year;
	
	@NotBlank(message = "Missing fields")
	private String licensePlate;
	
	@NotBlank(message = "Missing fields")
	private String model;
	
	@NotBlank(message = "Missing fields")
	private String color;
}
