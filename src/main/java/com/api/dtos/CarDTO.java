package com.api.dtos;

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
	
	private int year;
	
	private String licensePlate;
	
	private String model;
	
	private String color;
}
