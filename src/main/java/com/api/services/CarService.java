package com.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.dtos.CarDTO;

@Service
public interface CarService {

	List<CarDTO>findAll() throws SQLException;
	
	CarDTO findById(Integer idPessoa) throws SQLException;
	
	CarDTO save(CarDTO CarDTO) throws SQLException;
	
	void delete(Integer idCar) throws SQLException;
	
	Boolean existsLicensPlate(String licensePlate, Integer idCar);
	
}
