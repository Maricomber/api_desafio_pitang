package com.api.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_car", nullable = true)
	private Integer idCar;
	
	@Column(name = "year", nullable = false, length = 4)
	private int year;
	
	@Column(name = "license_plate", nullable = false)
	private String licensePlate;
	
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "color", nullable = false)
	private String color;

	@ManyToOne(cascade = CascadeType.MERGE,fetch =  FetchType.LAZY)  
    @JoinColumn(name="id_user")
	private Users users;
}
