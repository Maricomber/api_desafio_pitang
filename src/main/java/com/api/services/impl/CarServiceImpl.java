package com.api.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dtos.CarDTO;
import com.api.dtos.UserDTO;
import com.api.repositories.CarRepository;
import com.api.services.CarService;

@Service
public class CarServiceImpl implements CarService{

	private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	
	@Autowired
	CarRepository repository;
	
	@Override
	public List<CarDTO> findAll() throws SQLException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDTO findById(Integer idPessoa) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDTO save(CarDTO CarDTO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer idCar) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
