package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.api.entities.Car;

@Transactional(readOnly = true)
public interface CarRepository extends JpaRepository<Car, Integer>{
	Car findByIdCar(Integer idCar);
	Car findByLicensePlate(String licensePlate);
}
