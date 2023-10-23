package com.api.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dtos.CarDTO;
import com.api.entities.Car;
import com.api.enums.ErrorEnum;
import com.api.exception.ApiPitangException;
import com.api.repositories.CarRepository;
import com.api.services.CarService;

@Service
public class CarServiceImpl implements CarService{

private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	
	@Autowired
	CarRepository repository;

	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public List<CarDTO> findAll() throws SQLException {
		log.info("Searching all cars...");
		List<Car> cars = new ArrayList<>();
		List<CarDTO> carRetorno = new ArrayList<>();
		
		try {
			cars = this.repository.findAll();
			cars.stream().forEach(car->carRetorno.add(mapper.map(car, CarDTO.class)));
			log.info("Search completed successfully.");
			return carRetorno;
		}catch (Exception e) {
			msgErro = "Error searching all cars. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public CarDTO findById(Integer idCar) throws SQLException {
		log.info("Searching car...");
		Car car = new Car();
		try {
			car = this.repository.findByIdCar(idCar);
			if(car == null) {
				throw new NoResultException("No records.");
			}
			log.info("Car found.");
			return mapper.map(car, CarDTO.class);
		}catch (Exception e) {
			msgErro = "Error searching car. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public CarDTO save(CarDTO carDTO) throws SQLException {
		log.info("Saving car...");
		try {
			if(existsLicensPlate(carDTO.getLicensePlate(), carDTO.getIdCar())) {
				throw new ApiPitangException(ErrorEnum.PLATE_EXISTS);
			}
			Car car = mapper.map(carDTO, Car.class);
			return mapper.map(this.repository.save(car), CarDTO.class);
		}catch (Exception e) {
			msgErro = "Error saving car. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public void delete(Integer idCar) throws SQLException {
		Car car = new Car();
		log.info("Deleting car...");
		
		try{
			car = this.repository.findByIdCar(idCar);
			this.repository.delete(car);
		}catch (Exception e) {
			msgErro = "Error car cannot be deleted. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public Boolean existsLicensPlate(String licensePlate, Integer idCar) {
		log.info("Lincense car...");
		Car car = new Car();
	
		car = this.repository.findByLicensePlate(licensePlate);
		if(car == null || (car != null && idCar == car.getIdCar())) {
			return false;
		}
		log.info("Lincense found.");
		return true;
	}

}
