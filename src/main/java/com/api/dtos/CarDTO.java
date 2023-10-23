package com.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Size()
	@NotNull(message = "Missing fields")
	private int year;
	
	@NotBlank(message = "Missing fields")
	private String licensePlate;
	
	@NotBlank(message = "Missing fields")
	private String model;
	
	@NotBlank(message = "Missing fields")
	private String color;
}
